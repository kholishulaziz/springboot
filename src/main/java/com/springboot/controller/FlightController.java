package com.springboot.controller;

import com.springboot.model.Flight;
import com.springboot.model.FlightDTO;
import com.springboot.service.FlightService;
import com.springboot.util.BaseResponse;
import com.springboot.util.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Flight")
@Validated
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping(value = Const.API_FLIGHT, consumes = {"application/json"})
    public ResponseEntity<BaseResponse<FlightDTO>> addFlight(@RequestBody @Valid Flight flight) {
        BaseResponse<FlightDTO> response = new BaseResponse<>();
        try {
            FlightDTO newFlight = flightService.addFlight(flight);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(newFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = Const.API_FLIGHT)
    public ResponseEntity<BaseResponse<Collection<FlightDTO>>> getAllFlight(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer airlineId) {
        BaseResponse<Collection<FlightDTO>> response = new BaseResponse<>();
        try {
            List<FlightDTO> flight = flightService.getFlight(size, page, airlineId);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(flight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = Const.API_FLIGHT + "/{flightId}")
    public ResponseEntity<BaseResponse<FlightDTO>> getFlightById(@PathVariable Integer flightId) {
        BaseResponse<FlightDTO> response = new BaseResponse<>();
        try {
            FlightDTO flight = flightService.findFlight(flightId);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(flight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = Const.API_FLIGHT + "/{flightId}", consumes = {"application/json"})
    public ResponseEntity<BaseResponse<FlightDTO>> editFlight(@PathVariable Integer flightId, @RequestBody @Valid Flight flight) {
        BaseResponse<FlightDTO> response = new BaseResponse<>();
        try {
            FlightDTO editFlight = flightService.editFlight(flightId, flight);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(editFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = Const.API_FLIGHT + "/{flightId}")
    public ResponseEntity<BaseResponse> deleteFlight(@PathVariable Integer flightId) {
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

    @PutMapping(value = Const.API_FLIGHT + "/{flightId}" + Const.API_MISCELLANEOUS)
    public ResponseEntity<BaseResponse<FlightDTO>> addMiscellaneousIntoFlight(@PathVariable Integer flightId, @RequestBody String miscellaneous) {
        BaseResponse<FlightDTO> response = new BaseResponse<>();
        try {
            FlightDTO newFlight = flightService.addMiscellaneousIntoFlight(flightId, miscellaneous);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(newFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = Const.API_FLIGHT + "/{flightId}" + Const.API_PASSENGER + "/{passengerId}")
    public ResponseEntity<BaseResponse<FlightDTO>> addPasengerIntoFlight(@PathVariable Integer flightId, @PathVariable Integer passengerId) {
        BaseResponse<FlightDTO> response = new BaseResponse<>();
        try {
            FlightDTO newFlight = flightService.addPassengerIntoFlight(flightId, passengerId);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(newFlight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = Const.API_OTHER_FLIGHT)
    public ResponseEntity<BaseResponse<Collection<FlightDTO>>> getOtherFlight(
            @RequestParam(required = false) Integer airlineId) {
        BaseResponse<Collection<FlightDTO>> response = new BaseResponse<>();
        try {
            List<FlightDTO> flight = flightService.getOtherFlight(airlineId);
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(flight);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
