package com.feature.service.controller;

import com.feature.service.models.dto.FeatureTextRecord;
import com.feature.service.service.FeatureTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("feature/text")
public class FeatureTextController {

    @Autowired
    FeatureTextService featureTextService;

    @GetMapping
    Flux<FeatureTextRecord> getAllFeatures(){
        return featureTextService.listAllFeatures();
    }

    @PostMapping
    @Transactional
    Mono<Object> createFeature(@RequestBody FeatureTextRecord textFeature){
        return featureTextService.createFeature(textFeature);
    }

}
