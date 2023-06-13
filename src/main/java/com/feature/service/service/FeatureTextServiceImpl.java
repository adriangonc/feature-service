package com.feature.service.service;

import com.feature.service.models.FeatureText;
import com.feature.service.models.dto.FeatureTextRecord;
import com.feature.service.repository.FeatureTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeatureTextServiceImpl implements FeatureTextService {

    @Autowired
    FeatureTextRepository featureTextRepository;
    @Override
    public Mono<Object> createFeature(FeatureTextRecord feature) {
        var featureText = new FeatureText(feature.name(), feature.active(), feature.textValue());

        return featureTextRepository.save(featureText).map(savedFeature -> new FeatureTextRecord(
                savedFeature.getId(),
                savedFeature.getName(),
                savedFeature.isActive(),
                savedFeature.getTextValue()));
    }

    @Override
    public Flux<FeatureTextRecord> listAllFeatures() {
        return featureTextRepository.findAll().map(featureText -> new FeatureTextRecord(featureText.getId(),
                featureText.getName(),
                featureText.isActive(),
                featureText.getTextValue()));
    }

    @Override
    public Mono<Object> changeFeatureStatus(FeatureTextRecord featureTextRecord) {
        return null;
    }

    @Override
    public Mono<Object> deleteFeatureById(String id) {
        return null;
    }
}
