package com.target.myretail.integrations.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import static com.target.myretail.util.BaseConstants.*;

@Service
public class CommonUtil {

    public String getRedSkyUrl(long id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(RED_SKY_BASE_URL);
        uriBuilder.path(String.valueOf(id));
        uriBuilder.queryParam(EXCLUDES, EXCLUDES_PARAMS);
        return uriBuilder.build().encode().toUriString();
    }

    public String getPricingServiceUrl(long id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(PRICING_BASE_URL);
        uriBuilder.path(String.valueOf(id));
        return uriBuilder.build().encode().toUriString();
    }
}
