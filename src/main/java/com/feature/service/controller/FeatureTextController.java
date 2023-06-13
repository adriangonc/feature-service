package com.feature.service.controller;

import com.feature.service.models.dto.FeatureTextRecord;
import com.feature.service.service.FeatureTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("feature/text")
public class FeatureTextController {

    @Autowired
    FeatureTextService featureTextService;

    @GetMapping
    Flux<FeatureTextRecord> getAllFeatures(){
        return featureTextService.listAllFeatures();
    }

}
