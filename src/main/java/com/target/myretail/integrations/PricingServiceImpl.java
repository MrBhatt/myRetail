package com.target.myretail.integrations;

import com.target.myretail.integrations.utils.CommonUtil;
import com.target.myretail.integrations.utils.ExceptionHandler;
import com.target.myretail.models.ProductPriceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private static ExceptionHandler exceptionHandler;

    public ProductPriceDetail getProductPricing(long id) {

        RestTemplate restTemplate = new RestTemplate();
        ProductPriceDetail productPriceDetail = null;

        try {
            productPriceDetail = restTemplate.getForObject(commonUtil.getPricingServiceUrl(id), ProductPriceDetail.class);
        } catch (HttpClientErrorException ex) {
            exceptionHandler.handleHttpException(ex);
        }

        return productPriceDetail;
    }
}
