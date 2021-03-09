package com.springboot.service;

import com.springboot.model.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> getAirline();

    Airline getAirlineById(Integer id);

}
