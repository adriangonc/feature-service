package com.feature.service.controller;

import com.feature.service.exception.FeatureAlreadyExistsException;
import com.feature.service.exception.FeatureServiceException;
import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("feature/boolean")
public class FeatureBooleanControler {

    @Autowired
    FeatureService featureService;

    @GetMapping
    public Flux<FeatureBooleanRecord> getAllFeatures() {
        return featureService.listAllFeatures();
    }

    @PostMapping
    @Transactional
    public Mono<ResponseEntity<Object>> createFeature(@RequestBody FeatureBooleanRecord featureBoolean) {
        return featureService.createFeature(featureBoolean).map(feature -> ResponseEntity.ok(feature))
                .onErrorResume(error -> {
                    if (error instanceof FeatureAlreadyExistsException) {
                        return Mono.just(ResponseEntity.badRequest().body(error.getMessage()));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                    }
                });
    }

    @PutMapping
    @Transactional
    public Mono<ResponseEntity<Object>> changeFeatureStatus(@RequestBody FeatureBooleanRecord featureBoolean) {
        return featureService.changeFeatureStatus(featureBoolean).map(feature -> ResponseEntity.ok(feature))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(e.getMessage())));
    }

}
