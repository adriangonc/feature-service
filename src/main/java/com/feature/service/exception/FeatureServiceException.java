package com.feature.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;

abstract public class FeatureServiceException extends RuntimeException{

    public HttpStatus status = HttpStatus.BAD_REQUEST;

    public abstract String getMessage();
    public abstract ErrorResponse getErrorResponse();

    public HttpStatus getStatus(){
        return status;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
    }
}
