package com.org.exchangeproblem.mapper;

import com.org.exchangeproblem.data.MatchedExchangeDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MatchedExchangeDetailsMapper implements ResultSetExtractor<List<MatchedExchangeDetails>> {

    private static MatchedExchangeDetails mapExchangeData(ResultSet resultSet) throws SQLException{

        return MatchedExchangeDetails.builder()
                                     .id(UUID.fromString(resultSet.getString("id")))
                                     .buyer(resultSet.getString("buyer"))
                                     .seller(resultSet.getString( "seller"))
                                     .symbol(resultSet.getString("symbol"))
                                     .price(resultSet.getInt("price"))
                                     .date(resultSet.getDate("date"))
                                     .build();
    }

    @Override
    public List<MatchedExchangeDetails> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        var exchangeDataList = new ArrayList<MatchedExchangeDetails>();

        while (resultSet.next()){
            var exchangeDetails = mapExchangeData(resultSet);
            exchangeDataList.add(exchangeDetails);
        }
        return exchangeDataList;
    }
}
