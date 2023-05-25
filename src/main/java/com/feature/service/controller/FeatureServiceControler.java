package com.feature.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("feature/")
public class FeatureServiceControler {

    @GetMapping
    public void getFeatureByName(){

    }

}
