package com.feature.service.service;

import com.feature.service.models.dto.FeatureTextRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeatureTextBoolean {

    Mono<Object> createFeature(FeatureTextRecord feature);

    Flux<FeatureTextRecord> listAllFeatures();

    Mono<Object> changeFeatureStatus(FeatureTextRecord featureTextRecord);

    Mono<Object> deleteFeatureById(String id);

}
