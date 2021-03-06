package com.target.myretail.integrations;

import com.target.myretail.integrations.utils.CommonUtil;
import com.target.myretail.integrations.utils.ExceptionHandler;
import com.target.myretail.models.Price;
import com.target.myretail.models.ProductPriceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private static ExceptionHandler exceptionHandler;

    @Autowired
    private RestTemplate restTemplate;

    public ProductPriceDetail getProductPricing(long id) {

        ProductPriceDetail productPriceDetail = null;

        try {
            productPriceDetail = restTemplate.getForObject(commonUtil.getPricingServiceUrl(id), ProductPriceDetail.class);
        } catch (RestClientResponseException ex) {
            exceptionHandler.handleHttpException(ex);
        } catch (Exception ex) {
            exceptionHandler.handleGenericException(ex);
        }

        return productPriceDetail;
    }

    @Override
    public ProductPriceDetail setProductPricing(long id) {

        // create a dummy price object and return the same
        ProductPriceDetail productPriceDetail = new ProductPriceDetail();
        productPriceDetail.setId(id);

        Price price = new Price();
        price.setCurrencyCode("USD");
        price.setValue(20.48);

        productPriceDetail.setPrice(price);
        return productPriceDetail;
    }
}
