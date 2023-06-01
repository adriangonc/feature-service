package com.feature.service.controller;

import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("feature/boolean")
public class FeatureBooleanControler {

    @Autowired
    FeatureService featureService;

    @GetMapping
    public Flux<FeatureBooleanRecord> getAllFeatures(){
        return featureService.listAllFeatures();
    }

    @PostMapping
    public Mono<FeatureBooleanRecord> createFeature(@RequestBody FeatureBooleanRecord featureBoolean){
        return featureService.createFeature(featureBoolean);
    }

}
