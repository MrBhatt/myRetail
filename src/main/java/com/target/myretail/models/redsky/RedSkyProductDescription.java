package com.target.myretail.models.redsky;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RedSkyProductDescription {

    @JsonProperty("title")
    private String title;
}
