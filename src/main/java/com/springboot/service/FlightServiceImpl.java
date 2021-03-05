package com.springboot.service;

import com.springboot.model.Flight;
import com.springboot.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Page<Flight> getFlight(int size, int page) {
        return flightRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Flight findFlight(int flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if (!flight.isPresent())
            throw new RuntimeException("Flight with id: " + flightId + " doesn't exist");
        return flight.get();
    }

    @Override
    public Flight editFlight(int flightId, Flight flight) {
        findFlight(flightId);
        flight.setId(flightId);
        flightRepository.save(flight);
        return flight;
    }

}
