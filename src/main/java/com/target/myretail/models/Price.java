package com.target.myretail.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Price {

    private double value;

    @JsonProperty("currency_code")
    private String currencyCode;
}
