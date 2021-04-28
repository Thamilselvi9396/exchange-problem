package com.org.exchangeproblem.controller;

import com.org.exchangeproblem.data.MatchedExchangeDetails;
import com.org.exchangeproblem.data.UnMatchedExchangeDetails;
import com.org.exchangeproblem.service.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ExchangeController {

    Logger logger = LoggerFactory.getLogger(ExchangeController.class);

    @Autowired
    ExchangeService exchangeService;

    /**
     * This API will get the list of matched exchanges from
     * the matched_exchange table from DB.
     *
     * @param queryParams This contains the list of query params
     *                    from the user request.
     */
    @GetMapping("/get-matched-exchange-details")
    public List<MatchedExchangeDetails> getMatchedExchangeDetails(@RequestParam Map<String, String> queryParams){

        logger.info("The Query Params passed by the user is: {}", queryParams);

        return exchangeService.getMatchedExchangeDetails(queryParams);

    }

    /**
     * This API will get the list of unmatched exchanges from
     * the unmatched_exchange table from DB.
     *
     * @param queryParams This contains the list of query params
     *                    from the user request.
     */
    @GetMapping("/get-unmatched-exchange-details")
    public List<UnMatchedExchangeDetails> getUnmatchedExchangeDetails(@RequestParam Map<String, String> queryParams){

        logger.info("The Query Params passed by the user is: {}", queryParams);

        return exchangeService.getUnMatchedExchangeDetails(queryParams);

    }
}
