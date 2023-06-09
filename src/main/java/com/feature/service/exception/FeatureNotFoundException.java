package com.feature.service.exception;

import org.springframework.http.HttpStatus;

public class FeatureNotFoundException extends FeatureServiceException {

    private String featureName;

    public FeatureNotFoundException(String featureId){
        this.featureName = featureId;
    }

    @Override
    public String getMessage() {
        return "Feature: " + featureName + " não encontrada!";
    }

    @Override
    public HttpStatus getErrorResponse() {
        return HttpStatus.CONFLICT;
    }
}
