package com.feature.service.service;

import com.feature.service.repository.FeatureBooleanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FeatureServiceImplTest {

    private FeatureServiceImpl featureService;

    @Mock
    private FeatureBooleanRepository featureBooleanRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        featureService = new FeatureServiceImpl(featureBooleanRepository);
    }

}
