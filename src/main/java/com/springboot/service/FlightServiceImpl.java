package com.springboot.service;

import com.springboot.model.Flight;
import com.springboot.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Flight getFlightById(String id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (!flight.isPresent())
            throw new RuntimeException("Flight with id: " + id + " doesn't exist");
        return flight.get();
    }

    @Override
    public List<Flight> getFlight(Integer size, Integer page, Integer airlineId, String flightCode) {
        size = (size == null) ? 10: size;
        page = (page == null) ? 0: page;
        List<Flight> flights;
        if (airlineId != null && flightCode==null)
            flights = flightRepository.findByAirlineId(PageRequest.of(page, size), airlineId);
        else if (airlineId == null && flightCode!=null)
            flights = flightRepository.findByFlightCodeContaining(PageRequest.of(page, size), flightCode);
        else if (airlineId != null && flightCode!=null)
            flights = flightRepository.findByAirlineIdAndFlightCode(PageRequest.of(page, size), airlineId, flightCode);
        else
            flights = (flightRepository.findAll(PageRequest.of(page, size))).getContent();
        return flights;
    }

    @Override
    public Flight findFlight(String id) {
        return getFlightById(id);
    }

    @Override
    public Flight editFlight(String flightId, Flight flight) {
        flight.setId(flightId);
        flightRepository.save(flight);
        return flight;
    }

    @Override
    public void delete(String id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

}
