package com.target.myretail.integrations;

import com.target.myretail.integrations.utils.CommonUtil;
import com.target.myretail.integrations.utils.ExceptionHandler;
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
}
