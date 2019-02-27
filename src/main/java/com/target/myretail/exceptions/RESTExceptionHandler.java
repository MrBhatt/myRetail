package com.target.myretail.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RESTExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorMessage handleNotFound(NotFoundException ex) {
        return new ErrorMessage("not-found", "requested resource not found, " + ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ErrorMessage handleBadRequest(BadRequestException ex) {
        return new ErrorMessage("bad-request", "request not valid, " + ex.getMessage());
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(GenericException.class)
    @ResponseBody
    public ErrorMessage handleGenericEx(GenericException ex) {
        return new ErrorMessage("http-error", "request could not be processed, " + ex.getMessage());
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceNotAvailableException.class)
    @ResponseBody
    public ErrorMessage handleServiceUnavailableEx(ServiceNotAvailableException ex) {
        return new ErrorMessage("unknown-error", "an error occurred which prohibits request execution, " + ex.getMessage());
    }
}
