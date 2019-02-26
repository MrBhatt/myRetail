package com.target.myretail.models.redsky;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RedSkyProductDetails {

    @JsonProperty("product")
    private RedSkyProduct redSkyProduct;
}
