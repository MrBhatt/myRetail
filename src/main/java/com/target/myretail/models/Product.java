package com.target.myretail.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Product {

    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("current_price")
    private Price currentPrice;
}
