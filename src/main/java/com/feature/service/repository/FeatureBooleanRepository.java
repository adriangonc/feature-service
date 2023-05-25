package com.feature.service.repository;

import com.feature.service.models.FeatureBoolean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FeatureBooleanRepository extends ReactiveMongoRepository<FeatureBoolean, String> {
    Mono<FeatureBoolean> findByName(String name);

}
