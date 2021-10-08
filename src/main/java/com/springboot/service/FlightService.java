package com.springboot.service;

import com.springboot.model.Flight;

import java.util.List;

public interface FlightService {

    Flight addFlight(Flight flight);

    Flight getFlightById(String id);

    List<Flight> getFlight(Integer size, Integer page, Integer airlineId, String flightCode);

    Flight findFlight(String id);

    Flight editFlight(String id, Flight flight);

    void delete(String id);

}
