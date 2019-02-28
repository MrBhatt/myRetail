package com.target.myretail.integrations;

import com.target.myretail.integrations.utils.CommonUtil;
import com.target.myretail.integrations.utils.ExceptionHandler;
import com.target.myretail.models.Product;
import com.target.myretail.models.redsky.RedSkyItem;
import com.target.myretail.models.redsky.RedSkyProduct;
import com.target.myretail.models.redsky.RedSkyProductDescription;
import com.target.myretail.models.redsky.RedSkyProductDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RedSkyServiceImpl implements RedSkyService {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Autowired
    RestTemplate restTemplate;

    public RedSkyProductDetails getProductDetails(long id) {

        RedSkyProductDetails redSkyProductDetails = null;
        try {
            redSkyProductDetails = restTemplate.getForObject(commonUtil.getRedSkyUrl(id), RedSkyProductDetails.class);
        } catch (RestClientResponseException ex) {
            log.debug("A HTTP exception occurred while consuming REDSKY service {1}", ex.getStatusText());
            exceptionHandler.handleHttpException(ex);
        } catch (Exception ex) {
            log.debug("A HTTP exception occurred while consuming REDSKY service {1}", ex.getMessage());
            exceptionHandler.handleGenericException(ex);
        }

        return redSkyProductDetails;
    }

    @Override
    public RedSkyProductDetails createProduct(Product productPayload) {

        // extract values from payload and prepare RedSky compatible JSON
        RedSkyItem redSkyItem = new RedSkyItem();
        RedSkyProductDescription redSkyProductDescription = new RedSkyProductDescription();
        redSkyProductDescription.setTitle(productPayload.getName());
        redSkyItem.setProductDescription(redSkyProductDescription);

        RedSkyProduct redSkyProduct = new RedSkyProduct();

        // Assume the newly created product is returned from the RedSky API. Attach a new ID to it to simulate the same
        redSkyItem.setTcin(11223344);
        redSkyProduct.setRedSkyItem(redSkyItem);
        RedSkyProductDetails redSkyProductDetails = new RedSkyProductDetails();
        redSkyProductDetails.setRedSkyProduct(redSkyProduct);

        return redSkyProductDetails;
    }
}
