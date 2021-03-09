package com.springboot.service;

import com.springboot.model.Passenger;
import com.springboot.model.PassengerDTO;

import java.util.List;

public interface PassengerService {

    List<PassengerDTO> getPassenger();

    Passenger getPassengerById(Integer id);

}
