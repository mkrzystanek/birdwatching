package com.drosophila.birdwatching.controllers;

import com.drosophila.birdwatching.models.Trips;
import com.drosophila.birdwatching.repositories.TripsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripsController {

    @Autowired
    private TripsRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Trips> getAllTrips() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Trips getById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Trips createTrips(@Valid @RequestBody Trips trips) {
        trips.set_id(ObjectId.get());
        repository.save(trips);
        return trips;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrips(@PathVariable("id") ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
