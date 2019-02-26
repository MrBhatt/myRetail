package com.target.myretail.models.redsky;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RedSkyItem {

    private long tcin;

    @JsonProperty("product_description")
    private RedSkyProductDescription productDescription;
}
