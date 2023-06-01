package com.feature.service.service;

import com.feature.service.models.FeatureBoolean;
import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.repository.FeatureBooleanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureBooleanRepository featureBooleanRepository;

    @Override
    public Mono<FeatureBooleanRecord> createFeature(FeatureBooleanRecord feature) {
        FeatureBoolean featureBoolean = new FeatureBoolean(UUID.randomUUID().toString(), feature.name(), feature.active());

        return featureBooleanRepository.save(featureBoolean)
                .map(savedFeatureBoolean -> feature)
                .switchIfEmpty(Mono.error(new RuntimeException("Não foi possível salvar a feature, tente novamente mais tarde")));
    }

    @Override
    public Flux<FeatureBooleanRecord> listAllFeatures() {
        return featureBooleanRepository.findAll()
                .map(featureBoolean -> new FeatureBooleanRecord(
                        featureBoolean.getId(),
                        featureBoolean.getName(),
                        featureBoolean.isActive()
                ));
    }
}
