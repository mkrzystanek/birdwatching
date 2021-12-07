package com.drosophila.birdwatching.unit.services;

import com.drosophila.birdwatching.models.Trips;
import com.drosophila.birdwatching.repositories.TripsRepository;
import com.drosophila.birdwatching.services.TripsService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.drosophila.birdwatching.TestUtils.getTrips;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripsServiceTests {

    @Mock
    private TripsRepository repository;

    @InjectMocks
    private TripsService service;

    List<Trips> tripsList;

    @Before
    public void setUp() {
        tripsList = getTrips();
    }

    @Test
    public void findAllTrips() {
        given(repository.findAll()).willReturn(tripsList);
        List<Trips> retrievedObservations = service.findAll();

        Assert.assertEquals(
                "TripsService failed to retrieve all trips from repository!",
                tripsList,
                retrievedObservations
        );
    }

    @Test
    public void findByID() {
        Trips trip = tripsList.get(0);
        ObjectId objectId = new ObjectId(trip.get_id());

        given(repository.findBy_id(objectId)).willReturn(trip);
        Trips retrievedTrip = service.findById(objectId);

        Assert.assertEquals(
                "TripsService failed to retrieve trips by id",
                trip,
                retrievedTrip
        );
    }
}
