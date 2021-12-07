package com.drosophila.birdwatching.unit.controllers;

import com.drosophila.birdwatching.models.Trips;
import com.drosophila.birdwatching.services.TripsService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static com.drosophila.birdwatching.TestUtils.getTrips;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TripsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TripsService service;

    private List<Trips> tripsList;

    @Autowired
    private JacksonTester<Trips> json;

    @Before
    public void setUp() {
        tripsList = getTrips();
    }

    @Test
    public void getAllTrips() throws Exception {
        given(service.findAll()).willReturn(tripsList);

        mockMvc.perform(get(new URI("/trips/"))
                .accept(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(tripsList.size())));

        verify(service, times(1)).findAll();
    }

    @Test
    public void getTripById() throws Exception {
        Trips trip = tripsList.get(0);
        String id = trip.get_id();
        ObjectId objectId = new ObjectId(id);

        given(service.findById(objectId)).willReturn(trip);

        mockMvc.perform(get(new URI("/trips/" + id)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._id", is(trip.get_id())))
                .andExpect(jsonPath("$.date", is(trip.getDate())))
                .andExpect(jsonPath("$.localization", is(trip.getLocalization())))
                .andExpect(jsonPath("$.biome", is(trip.getBiome())))
                .andExpect(jsonPath("$.observations", hasSize(trip.getObservations().size())));

        verify(service, times(1)).findById(objectId);
    }

    @Test
    public void createTrip() throws Exception {
        Trips trip = tripsList.get(0);

        when(service.createTrip(trip)).thenReturn(trip);

        mockMvc.perform(post(new URI("/trips/"))
                .content(json.write(trip).getJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._id", is(trip.get_id())))
                .andExpect(jsonPath("$.date", is(trip.getDate())))
                .andExpect(jsonPath("$.localization", is(trip.getLocalization())))
                .andExpect(jsonPath("$.biome", is(trip.getBiome())))
                .andExpect(jsonPath("$.observations", hasSize(trip.getObservations().size())));
    }
}
