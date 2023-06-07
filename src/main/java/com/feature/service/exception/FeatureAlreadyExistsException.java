package com.feature.service.exception;

import org.springframework.http.HttpStatus;

public class FeatureAlreadyExistsException extends FeatureServiceException {

    private String featureName;

    public FeatureAlreadyExistsException(String featureName){
        this.featureName = featureName;
    }

    @Override
    public String getMessage() {
        return "Feature: " + featureName + " já cadastrada!";
    }

    @Override
    public HttpStatus getErrorResponse() {
        return HttpStatus.CONFLICT;
    }
}
