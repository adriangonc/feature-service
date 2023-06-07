package com.feature.service.service;

import com.feature.service.models.FeatureBoolean;
import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.repository.FeatureBooleanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FeatureServiceImplTest {

    private FeatureServiceImpl featureService;

    @Mock
    private FeatureBooleanRepository featureBooleanRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        featureService = new FeatureServiceImpl(featureBooleanRepository);
    }

    @Test
    public void createFeature_WhenFeatureNotExists_ShouldSafeFeatureBoolean() {
        //Arrange
        FeatureBooleanRecord featureBooleanRecord = new FeatureBooleanRecord("-1", "FEATURE_TEST_ADR-01", true);
        FeatureBoolean featureBoolean = new FeatureBoolean(featureBooleanRecord.id(),
                featureBooleanRecord.name(),
                featureBooleanRecord.active());
        when(featureBooleanRepository.findByName(featureBooleanRecord.name())).thenReturn(Mono.empty());
        when(featureBooleanRepository.save(any(FeatureBoolean.class))).thenReturn(Mono.just(featureBoolean));


        //Act
        Mono<Object> result = featureService.createFeature(featureBooleanRecord);

        //Assert
        //assertEquals(featureBoolean, result.block());
        verify(featureBooleanRepository, times(1)).findByName(featureBooleanRecord.name());

    }

}
