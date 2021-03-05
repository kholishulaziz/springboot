package com.springboot.controller;

import com.springboot.model.Flight;
import com.springboot.service.FlightService;
import com.springboot.util.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@Api(tags = "Flight")
@Validated
public class FlightController {

    private static final String API_FLIGHT = "/flight";
    private static final String MESSAGE_SUCCESS = "success";

    @Autowired
    private FlightService flightService;

    @PostMapping(value = API_FLIGHT, consumes = {"application/json"})
    public ResponseEntity<BaseResponse<Flight>> addFlight(@RequestBody @Valid Flight flight) {
        BaseResponse<Flight> response = new BaseResponse<>();
        try {
            Flight newFlight = flightService.addFlight(flight);
            response.setMessage(MESSAGE_SUCCESS);
            response.setBody(newFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = API_FLIGHT)
    public ResponseEntity<BaseResponse<Collection<Flight>>> getAllFlight(int size, int page) {
        BaseResponse<Collection<Flight>> response = new BaseResponse<>();
        try {
            Page<Flight> flight = flightService.getFlight(size, page);
            response.setMessage(MESSAGE_SUCCESS);
            response.setBody(flight.getContent());
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = API_FLIGHT + "/{flightId}")
    public ResponseEntity<BaseResponse<Flight>> getFlightById(@PathVariable int flightId) {
        BaseResponse<Flight> response = new BaseResponse<>();
        try {
            Flight flight = flightService.findFlight(flightId);
            response.setMessage(MESSAGE_SUCCESS);
            response.setBody(flight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = API_FLIGHT + "/{flightId}", consumes = {"application/json"})
    public ResponseEntity<BaseResponse<Flight>> editFlight(@PathVariable int flightId, @RequestBody @Valid Flight flight) {
        BaseResponse<Flight> response = new BaseResponse<>();
        try {
            Flight editFlight = flightService.editFlight(flightId, flight);
            response.setMessage(MESSAGE_SUCCESS);
            response.setBody(editFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
