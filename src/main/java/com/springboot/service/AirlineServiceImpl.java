package com.springboot.service;

import com.springboot.model.Airline;
import com.springboot.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public List<Airline> getAirline() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline getAirlineById(Integer id) {
        Optional<Airline> airline = airlineRepository.findById(id);
        if (!airline.isPresent())
            throw new RuntimeException("Airline with id: " + id + " doesn't exist");
        return airline.get();
    }

}
