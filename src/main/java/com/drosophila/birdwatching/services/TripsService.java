package com.drosophila.birdwatching.services;

import com.drosophila.birdwatching.models.Trips;
import com.drosophila.birdwatching.repositories.TripsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsService {

    @Autowired
    private TripsRepository repository;

    public List<Trips> findAll() {
        return repository.findAll();
    }

    public Trips findById(ObjectId id) {
        return repository.findBy_id(id);
    }

    public Trips createTrip(Trips trips) {
        trips.set_id(ObjectId.get());
        repository.save(trips);
        return trips;
    }

    public void deleteTripById(ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
