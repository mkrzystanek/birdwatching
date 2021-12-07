package com.drosophila.birdwatching.unit;

import com.drosophila.birdwatching.models.Observations;
import com.drosophila.birdwatching.services.ObservationsService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static com.drosophila.birdwatching.TestUtils.getObservations;
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
public class ObservationsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ObservationsService service;

    private List<Observations> observations;

    @Autowired
    private JacksonTester<Observations> json;

    @Before
    public void setUp() {
        observations = getObservations();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JacksonTester.initFields(this, mapper);
    }

    @Test
    public void getAllObservations() throws Exception {
        given(service.findAll())
                .willReturn(observations);

        mockMvc.perform(get(new URI("/observations/"))
                .accept(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(observations.size())));

        verify(service, times(1)).findAll();
    }

    @Test
    public void getObservationById() throws Exception {
        Observations observation = observations.get(0);
        String id = observation.get_id();

        given(service.findById(new ObjectId(id))).willReturn(observation);

        mockMvc.perform(get(new URI("/observations/" + id)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._id", is(observation.get_id())))
                .andExpect(jsonPath("$.species", is(observation.getSpecies())))
                .andExpect(jsonPath("$.weather", is(observation.getWeather())))
                .andExpect(jsonPath("$.localization", is(observation.getLocalization())))
                .andExpect(jsonPath("$.notes", is(observation.getNotes())))
                .andExpect(jsonPath("$.habitat", is(observation.getHabitat())))
                .andExpect(jsonPath("$.sex", is(observation.getSex())))
                .andExpect(jsonPath("$.age", is(observation.getAge())));
    }

    @Test
    public void createObservation() throws Exception {
        Observations observation = observations.get(0);

        when(service.createObservation(observation)).thenReturn(observation);

        mockMvc.perform(post(new URI("/observations/"))
                .content(json.write(observation).getJson())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._id", is(observation.get_id())))
                .andExpect(jsonPath("$.species", is(observation.getSpecies())))
                .andExpect(jsonPath("$.weather", is(observation.getWeather())))
                .andExpect(jsonPath("$.localization", is(observation.getLocalization())))
                .andExpect(jsonPath("$.notes", is(observation.getNotes())))
                .andExpect(jsonPath("$.habitat", is(observation.getHabitat())))
                .andExpect(jsonPath("$.sex", is(observation.getSex())))
                .andExpect(jsonPath("$.age", is(observation.getAge())));
    }
}
