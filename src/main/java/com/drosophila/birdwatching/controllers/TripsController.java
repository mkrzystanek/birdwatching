package com.drosophila.birdwatching.controllers;

import com.drosophila.birdwatching.models.Trips;
import com.drosophila.birdwatching.services.TripsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripsController {

    @Autowired
    private TripsService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Trips> getAllTrips() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Trips getById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Trips createTrips(@Valid @RequestBody Trips trips) {
        return service.createTrip(trips);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrips(@PathVariable("id") ObjectId id) {
        service.deleteTripById(id);
    }
}
