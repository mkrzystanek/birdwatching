package com.drosophila.birdwatching.controllers;

import com.drosophila.birdwatching.models.Observations;
import com.drosophila.birdwatching.repositories.ObservationsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/observations")
public class ObservationsController  {

    @Autowired
    private ObservationsRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Observations> getAllObservations() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Observations getById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Observations createObservation(@Valid @RequestBody Observations observation) {
        observation.set_id(ObjectId.get());
        repository.save(observation);
        return observation;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteObservation(@PathVariable("id") ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}