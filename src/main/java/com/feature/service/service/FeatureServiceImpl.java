package com.feature.service.service;

import com.feature.service.models.FeatureBoolean;
import com.feature.service.repository.FeatureBooleanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureBooleanRepository featureBooleanRepository;

    @Override
    public Mono<FeatureBoolean> createFeature(FeatureBoolean feature) {
        return featureBooleanRepository.save(feature);
    }

    @Override
    public Flux<FeatureBoolean> listAllFeatures() {
        return featureBooleanRepository.findAll();
    }
}
