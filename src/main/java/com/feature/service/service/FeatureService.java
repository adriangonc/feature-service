package com.feature.service.service;

import com.feature.service.models.FeatureBoolean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeatureService {

    Mono<FeatureBoolean> createFeature(FeatureBoolean feature);

    Flux<FeatureBoolean> listAllFeatures();

}
