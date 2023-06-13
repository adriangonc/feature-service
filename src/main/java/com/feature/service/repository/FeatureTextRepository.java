package com.feature.service.repository;

import com.feature.service.models.FeatureBoolean;
import com.feature.service.models.FeatureText;
import com.feature.service.service.FeatureTextService;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FeatureTextRepository extends ReactiveMongoRepository<FeatureText, String> {

    Mono<FeatureText> findByName(String name);

}
