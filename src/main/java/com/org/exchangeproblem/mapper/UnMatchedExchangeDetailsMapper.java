package com.org.exchangeproblem.mapper;

import com.org.exchangeproblem.data.UnMatchedExchangeDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UnMatchedExchangeDetailsMapper implements ResultSetExtractor<List<UnMatchedExchangeDetails>> {


    private static UnMatchedExchangeDetails mapExchangeData(ResultSet resultSet) throws SQLException{

        return UnMatchedExchangeDetails.builder()
                                       .id(UUID.fromString(resultSet.getString("id")))
                                       .party(resultSet.getString("party"))
                                       .symbol(resultSet.getString("symbol"))
                                       .price(resultSet.getInt("price"))
                                       .build();
    }

    @Override
    public List<UnMatchedExchangeDetails> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        var unmatchedExchangeDataList = new ArrayList<UnMatchedExchangeDetails>();

        while (resultSet.next()){
            var unmatchedExchangeDetails = mapExchangeData(resultSet);
            unmatchedExchangeDataList.add(unmatchedExchangeDetails);
        }
        return unmatchedExchangeDataList;
    }
}
