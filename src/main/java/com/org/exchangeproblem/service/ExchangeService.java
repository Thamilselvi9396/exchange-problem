package com.org.exchangeproblem.service;

import com.org.exchangeproblem.data.MatchedExchangeDetails;
import com.org.exchangeproblem.data.UnMatchedExchangeDetails;
import com.org.exchangeproblem.repository.ExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExchangeService {

    Logger logger = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    ExchangeRepository exchangeRepository;

    /**
     * This method will receive the input from the controller
     * and pass on the information to repository to retrieve
     * data from the matched_exchange table.
     *
     * @param queryParams This contains the list of query params
     *                    from the user request.
     */
    public List<MatchedExchangeDetails> getMatchedExchangeDetails(Map<String, String> queryParams) {

        return exchangeRepository.findMatchedExchangeDetails(queryParams);
    }

    /**
     * This method will receive the input from the controller
     * and pass on the information to repository to retrieve
     * data from the unmatched_exchange table.
     */
    public List<UnMatchedExchangeDetails> getUnMatchedExchangeDetails(Map<String, String> queryParams) {

        return exchangeRepository.findUnMatchedExchangeDetails(queryParams);
    }
}
