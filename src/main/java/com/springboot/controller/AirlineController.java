package com.springboot.controller;

import com.springboot.model.Airline;
import com.springboot.service.AirlineService;
import com.springboot.util.BaseResponse;
import com.springboot.util.Const;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Airline")
@Validated
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping(value = Const.API_AIRLINES)
    public ResponseEntity<BaseResponse<Collection<Airline>>> getAllAirline() {
        BaseResponse<Collection<Airline>> response = new BaseResponse<>();
        try {
            List<Airline> airline = airlineService.getAirline();
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(airline);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
