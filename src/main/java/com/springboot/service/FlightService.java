package com.springboot.service;

import com.springboot.model.Flight;
import com.springboot.model.FlightDTO;

import java.util.List;

public interface FlightService {

    FlightDTO addFlight(Flight flight);

    Flight getFlightById(Integer id);

    List<FlightDTO> getFlight(Integer size, Integer page, Integer airlineId, String flightCode);

    FlightDTO findFlight(Integer id);

    FlightDTO editFlight(Integer id, Flight flight);

    void delete(Integer id);

    FlightDTO addPassengerIntoFlight(Integer flightId, Integer passengerId);

    FlightDTO addMiscellaneousIntoFlight(Integer flightId, String strMiscellaneous);

    List<FlightDTO> getOtherFlight(Integer airlineId);

}
