package com.org.exchangeproblem.data;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class MatchedExchangeDetails {

    UUID id;
    String seller;
    String buyer;
    String symbol;
    Number price;
    Date date;

}
