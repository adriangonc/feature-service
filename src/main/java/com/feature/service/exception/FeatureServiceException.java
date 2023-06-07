package com.feature.service.exception;

import org.springframework.http.HttpStatus;

abstract public class FeatureServiceException extends RuntimeException{

    public HttpStatus status = HttpStatus.BAD_REQUEST;

    public abstract String getMessage();
    public abstract HttpStatus getErrorResponse();

    public HttpStatus getStatus(){
        return status;
    }

    public void setStatus(HttpStatus status){
        this.status = status;
    }
}
