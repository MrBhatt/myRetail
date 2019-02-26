package com.target.myretail.models.redsky;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RedSkyProduct {

    @JsonProperty("item")
    private RedSkyItem redSkyItem;
}
