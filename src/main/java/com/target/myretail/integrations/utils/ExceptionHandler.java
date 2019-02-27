package com.target.myretail.integrations.utils;

import com.target.myretail.exceptions.BadRequestException;
import com.target.myretail.exceptions.GenericException;
import com.target.myretail.exceptions.NotFoundException;
import com.target.myretail.exceptions.ServiceNotAvailableException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

@Service
public class ExceptionHandler {

    public void handleHttpException(RestClientResponseException ex) {
        switch (ex.getRawStatusCode()) {
            case 404:
                throw new NotFoundException(ex.getMessage());
            case 400:
                throw new BadRequestException(ex.getMessage());
                // catch all.. throw a generic exception
            default:
                throw new GenericException(ex.getMessage());
        }
    }

    public void handleGenericException(Exception ex) {
        throw new ServiceNotAvailableException(ex.getMessage());
    }
}
