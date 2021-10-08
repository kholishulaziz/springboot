package com.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.model.Flight;
import com.springboot.service.FlightService;
import com.springboot.util.Const;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Autowired
    private ObjectMapper objectMapper;

    private static Flight flight;

    @BeforeClass
    public static void setUpData() {
        flight = new Flight();
        flight.setFlightCode("GA303");
    }

    @Test
    public void givenRequestWhenAddFlightThenReturnOk() throws Exception {
        Mockito.when(flightService.addFlight(ArgumentMatchers.any(Flight.class)))
                .thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api" + Const.API_FLIGHT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flight)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(Const.MESSAGE_SUCCESS)))
                .andExpect(content().string(containsString(objectMapper.writeValueAsString(flight))))
                .andDo(MockMvcResultHandlers.print());
    }
}
