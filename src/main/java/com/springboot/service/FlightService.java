package com.springboot.service;

import com.springboot.model.Flight;
import org.springframework.data.domain.Page;

public interface FlightService {

    Flight addFlight(Flight flight);

    Page<Flight> getFlight(int size, int page);

    Flight findFlight(int id);

    Flight editFlight(int id, Flight flight);

}
