package com.drosophila.birdwatching;

import com.drosophila.birdwatching.models.Observations;
import com.drosophila.birdwatching.repositories.ObservationsRepository;
import com.drosophila.birdwatching.services.ObservationsService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.drosophila.birdwatching.TestUtils.getObservations;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ObservationsServiceTests {

    @Mock
    private ObservationsRepository repository;

    @InjectMocks
    private ObservationsService service;

    private List<Observations> observations;

    @Before
    public void setUp() {
        observations = getObservations();
    }

    @Test
    public void findAllObservations() {
        given(repository.findAll()).willReturn(observations);
        List<Observations> retrievedObservations = service.findAll();
        Assert.assertEquals(
                "ObservationsService failed to retrieve all observations from repository!",
                observations,
                retrievedObservations
        );
    }

    @Test
    public void findById() {
        Observations observation = observations.get(0);
        ObjectId observationId = new ObjectId(observation.get_id());

        given(repository.findBy_id(observationId)).willReturn(observation);

        Observations retrievedObservation = service.findById(observationId);

        Assert.assertEquals(
                "ObservationsService failed to retrieve observation by id",
                observation,
                retrievedObservation
        );
    }

    @Test
    public void saveObservation() {
        Observations observation = observations.get(0);

        when(repository.save(observation)).thenReturn(observation);

        Observations savedObservation = service.createObservation(observation);

        Assert.assertEquals(
                "ObservationsService method 'createObservation()' din not return correct Observations object",
                observation,
                savedObservation
        );
    }
}
