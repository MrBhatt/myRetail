package com.target.myretail.exceptions;

public class ServiceNotAvailableException extends RuntimeException {

    /**
     * All HTTP exceptions other than 4XX are handled here
     **/
    public ServiceNotAvailableException(String message) {
        super(message);
    }
}
