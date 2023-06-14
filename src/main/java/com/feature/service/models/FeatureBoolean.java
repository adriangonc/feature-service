package com.feature.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document(collection = "feature_booleans")
public class FeatureBoolean {

    @Id
    private String id;

    @NotNull
    @NotEmpty(message = "Field name can't be empty or null!")
    @Min(value = 5, message = "Field name can't be lower than 5 characters")
    private String name;

    @NotNull
    @NotEmpty(message = "Field active can't be empty or null!")
    private boolean active;

    public FeatureBoolean() {
        this.id = getFeatureId();
    }

    private static String getFeatureId() {
        return "FEATURE_" + UUID.randomUUID().toString();
    }

    public FeatureBoolean(String name, boolean active) {
        this.id = getFeatureId();
        this.name = name;
        this.active = active;
    }

    public FeatureBoolean(String id, String name, boolean active) {
        this.id = id;
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
