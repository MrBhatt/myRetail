package com.target.myretail.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Product {
    private long id;
    private String name;
    @JsonProperty("current_price")
    private Price currentPrice;
}
