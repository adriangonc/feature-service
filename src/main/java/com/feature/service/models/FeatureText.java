package com.feature.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "feature_text")
public class FeatureText {
    @Id
    private String id;

    private String userId;

    private String name;

    private boolean active;

    private String textValue;

    public FeatureText(){
        this.id = getFeatureId();
    }

    private static String getFeatureId() {
        return "FEATURE_" + UUID.randomUUID().toString();
    }

    public FeatureText(String name, boolean active, String value) {
        this.id = getFeatureId();
        this.name = name;
        this.active = active;
        this.textValue = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
}
