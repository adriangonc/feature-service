package com.feature.service.controller;

import com.feature.service.models.FeatureBoolean;
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
    public Flux<FeatureBoolean> getAllFeatures(){
        return featureService.listAllFeatures();
    }

    @PostMapping
    public Mono<FeatureBoolean> createFeature(@RequestBody FeatureBoolean featureBoolean){
        return featureService.createFeature(featureBoolean);
    }

}
