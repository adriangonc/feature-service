package com.feature.service.service;

import com.feature.service.exception.FeatureAlreadyExistsException;
import com.feature.service.models.FeatureBoolean;
import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.repository.FeatureBooleanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
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
    public void createFeature_WhenFeatureNotExists_ShouldCreateSafeFeatureBoolean() {
        //Arrange
        FeatureBooleanRecord featureBooleanRecord = new FeatureBooleanRecord("","FEATURE_TEST_ADR-01", true);
        FeatureBoolean featureBoolean = new FeatureBoolean(
                featureBooleanRecord.name(),
                featureBooleanRecord.active());
        when(featureBooleanRepository.findByName(featureBooleanRecord.name())).thenReturn(Mono.empty());
        when(featureBooleanRepository.save(any(FeatureBoolean.class))).thenReturn(Mono.just(featureBoolean));


        //Act
        Mono<Object> result = featureService.createFeature(featureBooleanRecord);

        //Assert
        assertEquals(featureBoolean.getName(), ((FeatureBoolean) result.block()).getName());
        verify(featureBooleanRepository, times(1)).findByName(featureBooleanRecord.name());
        verify(featureBooleanRepository, times(1)).save(any(FeatureBoolean.class));

    }

    @Test
    public void returnFeatureAlreadyExist_WhenFeatureExists_ShouldNotCreateSafeFeatureBoolean() {
        // Arrange
        FeatureBooleanRecord featureRecord = new FeatureBooleanRecord("","Feature1", true);
        FeatureBoolean existingFeature = new FeatureBoolean("Feature1", true);

        when(featureBooleanRepository.findByName(featureRecord.name())).thenReturn(Mono.just(existingFeature));
        when(featureBooleanRepository.save(any(FeatureBoolean.class))).thenReturn(Mono.just(existingFeature));

        // Act & Assert
        assertThrows(FeatureAlreadyExistsException.class, () -> featureService.createFeature(featureRecord).block());
        verify(featureBooleanRepository, times(1)).findByName(featureRecord.name());

    }

}
