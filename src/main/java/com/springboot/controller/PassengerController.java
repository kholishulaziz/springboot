package com.springboot.controller;

import com.springboot.model.PassengerDTO;
import com.springboot.service.PassengerService;
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
@Api(tags = "Passenger")
@Validated
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping(value = Const.API_PASSENGER)
    public ResponseEntity<BaseResponse<Collection<PassengerDTO>>> getAllPassenger() {
        BaseResponse<Collection<PassengerDTO>> response = new BaseResponse<>();
        try {
            List<PassengerDTO> passenger = passengerService.getPassenger();
            response.setMessage(Const.MESSAGE_SUCCESS);
            response.setBody(passenger);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
