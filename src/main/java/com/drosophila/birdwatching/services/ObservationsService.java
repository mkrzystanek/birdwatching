package com.drosophila.birdwatching.services;

import com.drosophila.birdwatching.models.Observations;
import com.drosophila.birdwatching.repositories.ObservationsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationsService {

    @Autowired
    private ObservationsRepository repository;

    public List<Observations> findAll() {
        return repository.findAll();
    }

    public Observations findById(ObjectId id) {
        return repository.findBy_id(id);
    }

    public Observations createObservation(Observations observation) {
        return repository.save(observation);
    }

    public void deleteObservationById(ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
