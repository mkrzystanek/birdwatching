package com.drosophila.birdwatching;

import com.drosophila.birdwatching.enums.Age;
import com.drosophila.birdwatching.enums.Sex;
import com.drosophila.birdwatching.models.Observations;
import com.drosophila.birdwatching.services.ObservationsService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ObservationsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ObservationsService service;

    private List<Observations> observations;

    @Before
    public void setUp() {
        observations = getObservations();
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
}
