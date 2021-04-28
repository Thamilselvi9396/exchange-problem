package com.org.exchangeproblem.repository;

import com.org.exchangeproblem.data.MatchedExchangeDetails;
import com.org.exchangeproblem.data.UnMatchedExchangeDetails;
import com.org.exchangeproblem.mapper.MatchedExchangeDetailsMapper;
import com.org.exchangeproblem.mapper.UnMatchedExchangeDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Repository
public class ExchangeRepository {

    Logger logger = LoggerFactory.getLogger(ExchangeRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * This method will execute the query and map the data from the
     * matched_exchange table with java POJO class data members and
     * return the result to the service layer.
     *
     * @param queryParams This contains the list of query params
     *                    from the user request.
     */
    public List<MatchedExchangeDetails> findMatchedExchangeDetails(Map<String, String> queryParams) {

        var MatchedExchangeSelectQuery = "select id, seller, buyer, symbol, price, date from exchange.matched_exchange";

        var sql = constructSelectQuery(queryParams, MatchedExchangeSelectQuery);

        return jdbcTemplate.query(sql,
                                  new MatchedExchangeDetailsMapper());
    }

    /**
     * This method will execute the query and map the data from the
     * unmatched_exchange table with java POJO class data members and
     * return the result to the service layer.
     */
    public List<UnMatchedExchangeDetails> findUnMatchedExchangeDetails(Map<String, String> queryParams) {

        var unMatchedExchangeSelectQuery = "select id, party, symbol, price from exchange.unmatched_exchange";

        var sql = constructSelectQuery(queryParams, unMatchedExchangeSelectQuery);

        return jdbcTemplate.query(sql,
                                  new UnMatchedExchangeDetailsMapper());
    }

    /**
     * This method constructs the select query. If the user passes filter
     * then where clause will be constructed. Else if the user does not pass
     * filter then where clause wont be constructed.
     *
     * @param queryParams This contains the list of query params
     *                    from the user request.
     */
    private String constructSelectQuery(Map<String, String> queryParams,
                                        String selectQuery) {

        try{
        if(!CollectionUtils.isEmpty(queryParams)) {
            logger.info("The user request contains filters {}", queryParams);

            for (Map.Entry<String, String> entry : queryParams.entrySet()) {

                var filterValue = addSingleQuote(entry.getValue());

                var clause = String.format("%s = %s", entry.getKey(), filterValue);

                logger.info("Appending EQUAL TO to the filter key and value {}", clause);

                logger.info("Appending WHERE to the query....");

                var sql = String.format("%s WHERE %s", selectQuery, clause);
                logger.info("The constructed query with filters {}", sql);

                return sql;
            }
        }
        else {
            logger.info("The request does not filters {}", queryParams);
            logger.info("The default select query is executed {}", selectQuery);
            return selectQuery;
        }
        }
        catch(Exception exception){
            throw new RuntimeException("The query construction failed due to: ", exception);
        }
        return null;
    }

    /**
     * This method will append the filter value with single quotes to
     * query the database table
     *
     * @param filterValue This contains value which will be appended
     *                    with single quote.
     */
    public static String addSingleQuote(String filterValue) {
        return new StringBuilder()
                    .append('\'')
                    .append(filterValue)
                    .append('\'')
                    .toString();
    }
}
