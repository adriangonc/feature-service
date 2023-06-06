package com.feature.service.service;

import com.feature.service.models.dto.FeatureBooleanRecord;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeatureService {

    Mono<Object> createFeature(FeatureBooleanRecord feature);

    Flux<FeatureBooleanRecord> listAllFeatures();

}
