package com.feature.service.service;

import com.feature.service.models.dto.FeatureBooleanRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeatureBooleanService {

    Mono<Object> createFeature(FeatureBooleanRecord feature);

    Flux<FeatureBooleanRecord> listAllFeatures();

    Mono<Object> changeFeatureStatus(FeatureBooleanRecord featureBooleanRecord);

    Mono<Object> deleteFeatureById(String id);
}
