package com.drosophila.birdwatching.controllers;

import com.drosophila.birdwatching.models.Observations;
import com.drosophila.birdwatching.services.ObservationsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private ObservationsService service;

    @GetMapping(value = "/")
    public List<Observations> getAllObservations() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Observations getById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }

    @PostMapping(value = "/")
    public Observations createObservation(@Valid @RequestBody Observations observation) {
        return service.createObservation(observation);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteObservation(@PathVariable("id") ObjectId id) {
        service.deleteObservationById(id);
    }
}
