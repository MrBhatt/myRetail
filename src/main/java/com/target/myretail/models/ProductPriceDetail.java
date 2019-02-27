package com.target.myretail.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductPriceDetail {
    @JsonProperty(value = "product_id")
    private long id;
    private Price price;
}
