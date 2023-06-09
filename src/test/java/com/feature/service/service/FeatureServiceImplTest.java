package com.feature.service.service;

import com.feature.service.exception.FeatureAlreadyExistsException;
import com.feature.service.models.FeatureBoolean;
import com.feature.service.models.dto.FeatureBooleanRecord;
import com.feature.service.repository.FeatureBooleanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FeatureServiceImplTest {

    private FeatureBooleanServiceImpl featureService;

    @Mock
    private FeatureBooleanRepository featureBooleanRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        featureService = new FeatureBooleanServiceImpl(featureBooleanRepository);
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

    @Test
    public void listAllFeatures_shouldReturnAllFeatures(){
        //Arrange
        FeatureBoolean feature1 = new FeatureBoolean("Feature1", true);
        FeatureBoolean feature2 = new FeatureBoolean("Feature2", false);

        when(featureBooleanRepository.findAll()).thenReturn(Flux.just(feature1, feature2));

        //Act
        Flux<FeatureBooleanRecord> result = featureService.listAllFeatures();

        //Assert
        assertEquals(2, result.count().block());
    }

    @Test
    public void editFeature_ShouldEditFeatureBoolean() {
        //Arrange
        FeatureBooleanRecord featureBooleanRecord = new FeatureBooleanRecord("","FEATURE_TEST_ADR-01", true);
        FeatureBoolean featureBoolean = new FeatureBoolean(
                featureBooleanRecord.name(),
                featureBooleanRecord.active());
        when(featureBooleanRepository.findByName(featureBooleanRecord.name())).thenReturn(Mono.just(featureBoolean));
        when(featureBooleanRepository.save(any(FeatureBoolean.class))).thenReturn(Mono.just(featureBoolean));

        //Act
        //Mono<Object> createdFeature = featureService.createFeature(featureBooleanRecord);

        FeatureBooleanRecord editedFeature = new FeatureBooleanRecord("","FEATURE_TEST_ADR-01", false);

        Mono<Object> result = featureService.changeFeatureStatus(editedFeature);

        //Assert
        assertEquals(false, ((FeatureBooleanRecord) result.block()).active());
        verify(featureBooleanRepository, times(1)).findByName(featureBooleanRecord.name());
        verify(featureBooleanRepository, times(1)).save(any(FeatureBoolean.class));

    }

    //@Test
    public void deleteFeature_ShouldDeleteFeatureBoolean() {
        //Arrange
        String featureId = "123";
        FeatureBooleanRecord featureBooleanRecord = new FeatureBooleanRecord(featureId,"FEATURE_TEST_ADR-01", true);
        FeatureBoolean featureBoolean = new FeatureBoolean(
                featureBooleanRecord.id(),
                featureBooleanRecord.name(),
                featureBooleanRecord.active());
        when(featureBooleanRepository.findById(featureId)).thenReturn(Mono.just(featureBoolean));

        //Act
        //Mono<Object> createdFeature = featureService.createFeature(featureBooleanRecord);

        Mono<Object> result = featureService.deleteFeatureById(featureId);

        //Assert
        assertEquals(Mono.empty(), result.block());
        verify(featureBooleanRepository, times(1)).findById(featureId);
        verify(featureBooleanRepository, times(1)).delete(any(FeatureBoolean.class));

    }

}
