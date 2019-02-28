package com.target.myretail.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.target.myretail.models.Price;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * This class represents the product information as returned in a response or recieved in a POST request
 */
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
