package com.org.exchangeproblem.data;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class UnMatchedExchangeDetails {

    UUID id;
    String party;
    String symbol;
    Number price;
}
