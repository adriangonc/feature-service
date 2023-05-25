package com.feature.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collation = "feature_booleans")
public class FeatureBoolean {

    @Id
    private String id;
    private String name;
    private boolean active;

    public FeatureBoolean(){
        this.id = getFeatureId();
    }

    private static String getFeatureId() {
        return "FEATURE_" + UUID.randomUUID().toString();
    }


    public FeatureBoolean(String id, String name, boolean active) {
        this.id = getFeatureId();
        this.name = name;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
