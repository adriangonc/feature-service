package com.feature.service.service;

import com.feature.service.exception.FeatureAlreadyExistsException;
import com.feature.service.exception.FeatureNotFoundException;
import com.feature.service.models.FeatureBoolean;
import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.repository.FeatureBooleanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeatureBooleanServiceImpl implements FeatureBooleanService {

    public static final String NAO_FOI_POSSIVEL_SALVAR = "Não foi possível salvar a feature, tente novamente mais tarde!";
    @Autowired
    FeatureBooleanRepository featureBooleanRepository;

    public FeatureBooleanServiceImpl(FeatureBooleanRepository featureBooleanRepository) {
        this.featureBooleanRepository = featureBooleanRepository;
    }

    @Override
    public Mono<Object> createFeature(FeatureBooleanRecord feature) {

        FeatureBoolean featureBoolean = new FeatureBoolean(feature.name(), feature.active());

        return featureBooleanRepository.findByName(feature.name())
                .flatMap(existingFeature -> Mono.error(new FeatureAlreadyExistsException(feature.name())))
                .switchIfEmpty(featureBooleanRepository.save(featureBoolean)
                        .map(savedFeatureBoolean -> featureBoolean)
                        .switchIfEmpty(Mono.error(new RuntimeException(NAO_FOI_POSSIVEL_SALVAR))));
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

    @Override
    public Mono<Object> changeFeatureStatus(FeatureBooleanRecord featureBooleanRecord) {
        return featureBooleanRepository.findByName(featureBooleanRecord.name())
                .flatMap(existingFeature -> {
                    existingFeature.setActive(featureBooleanRecord.active());
                    return featureBooleanRepository.save(existingFeature)
                            .map(updateFeature -> new FeatureBooleanRecord(
                                    updateFeature.getId(), updateFeature.getName(), updateFeature.isActive()));
                });

    }

    @Override
    public Mono<Object> deleteFeatureById(String id) {
        return featureBooleanRepository.findById(id)
                .switchIfEmpty(Mono.error(new FeatureNotFoundException(id)))
                .flatMap(featureToDelete -> featureBooleanRepository.delete(featureToDelete));

    }
}
