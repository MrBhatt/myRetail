package com.target.myretail.integrations;

import com.target.myretail.exceptions.BadRequestException;
import com.target.myretail.exceptions.GenericException;
import com.target.myretail.exceptions.NotFoundException;
import com.target.myretail.models.redsky.RedSkyProductDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RedSkyService {

    private static final String RED_SKY_BASE_URL = "https://redsky.target.com/v2/pdp/tcin/";
    private static final String EXCLUDES = "excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_statistics,question_answer_statistics,rating_and_review_reviews";
    private static final String QUERY_SEPARATOR = "?";

    public RedSkyProductDetails getProductDetails(long id) {
        String url = RED_SKY_BASE_URL.concat(String.valueOf(id)).concat(QUERY_SEPARATOR).concat(EXCLUDES);

        RestTemplate restTemplate = new RestTemplate();
        RedSkyProductDetails redSkyProductDetails;

        try {
            redSkyProductDetails = restTemplate.getForObject(url, RedSkyProductDetails.class);
        } catch (HttpClientErrorException ex) {
            switch (ex.getStatusCode()) {
                case NOT_FOUND:
                    throw new NotFoundException(ex.getMessage());
                case BAD_REQUEST:
                    throw new BadRequestException(ex.getMessage());
                    // catch all.. throw a generic exception
                default:
                    throw new GenericException(ex.getMessage());
            }
        }
        return redSkyProductDetails;
    }
}
