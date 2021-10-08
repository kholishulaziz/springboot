package com.springboot.controller;

import com.springboot.model.Flight;
import com.springboot.service.FlightService;
import com.springboot.util.BaseResponse;
import com.springboot.util.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(value = Const.API_FLIGHT, consumes = {"application/json"})
    public ResponseEntity<BaseResponse<Flight>> addFlight(@RequestBody Flight flight) {
        BaseResponse<Flight> response = new BaseResponse<>();
        try {
            Flight newFlight = flightService.addFlight(flight);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(newFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = Const.API_FLIGHTS)
    public ResponseEntity<BaseResponse<Collection<Flight>>> getAllFlight(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer airlineId,
            @RequestParam(required = false) String flightCode) {
        BaseResponse<Collection<Flight>> response = new BaseResponse<>();
        try {
            List<Flight> flight = flightService.getFlight(size, page, airlineId, flightCode);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(flight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = Const.API_FLIGHT + "/{flightId}")
    public ResponseEntity<BaseResponse<Flight>> getFlightById(@PathVariable String flightId) {
        BaseResponse<Flight> response = new BaseResponse<>();
        try {
            Flight flight = flightService.findFlight(flightId);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(flight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = Const.API_FLIGHT + "/{flightId}", consumes = {"application/json"})
    public ResponseEntity<BaseResponse<Flight>> editFlight(@PathVariable String flightId, @RequestBody Flight flight) {
        BaseResponse<Flight> response = new BaseResponse<>();
        try {
            Flight editFlight = flightService.editFlight(flightId, flight);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(editFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = Const.API_FLIGHT + "/{flightId}")
    public ResponseEntity<BaseResponse> deleteFlight(@PathVariable String flightId) {
        BaseResponse response = new BaseResponse<>();
        try {
            flightService.delete(flightId);
            response.setMessage(Const.MESSAGE_SUCCESS);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
