package com.springboot.service;

import com.springboot.model.Flight;
import com.springboot.model.FlightDTO;
import com.springboot.model.Miscellaneous;
import com.springboot.model.Passenger;
import com.springboot.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private MiscellaneousService miscellaneousService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WebClient webClient;

    @Value("${api.other-flight.url}")
    private String otherFlightURL;

    @Override
    public FlightDTO addFlight(Flight flight) {
        flight.setAirline(airlineService.getAirlineById(flight.getAirlineId()));
        Flight newFlight = flightRepository.save(flight);
        FlightDTO flightDTO = convertToFlightDTO(newFlight);
        return flightDTO;
    }

    @Override
    public Flight getFlightById(Integer id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (!flight.isPresent())
            throw new RuntimeException("Flight with id: " + id + " doesn't exist");
        return flight.get();
    }

    @Override
    public List<FlightDTO> getFlight(Integer size, Integer page, Integer airlineId, String flightCode) {
        size = (size == null) ? 10: size;
        page = (page == null) ? 0: page;
        List<Flight> flights;
            if (airlineId != null && flightCode==null)
                flights = flightRepository.findByAirlineId(PageRequest.of(page, size), airlineId);
            else if (airlineId == null && flightCode!=null)
                flights = flightRepository.findByFlightCodeContainingOrderByIdAsc(PageRequest.of(page, size), flightCode);
            else if (airlineId != null && flightCode!=null)
                flights = flightRepository.findByAirlineAndFlightCode(PageRequest.of(page, size), airlineId, flightCode);
            else
                flights = flightRepository.findAllByOrderByIdAsc(PageRequest.of(page, size));
        List<FlightDTO> flightsDTO = flights.stream().map(this::convertToFlightDTO)
                .collect(Collectors.toList());
        return flightsDTO;
    }

    @Override
    public FlightDTO findFlight(Integer id) {
        Flight flight = getFlightById(id);
        FlightDTO flightDTO = convertToFlightDTO(flight);
        return flightDTO;
    }

    @Override
    public FlightDTO editFlight(Integer flightId, Flight flight) {
        Flight oldFlight = getFlightById(flightId);
        flight.setId(flightId);
        flight.setAirline(airlineService.getAirlineById(flight.getAirlineId()));
        flight.setPassengers(oldFlight.getPassengers());
        flight.setMiscellaneous(oldFlight.getMiscellaneous());
        flightRepository.save(flight);
        FlightDTO flightDTO = convertToFlightDTO(flight);
        return flightDTO;
    }

    @Override
    public void delete(Integer id) {
        Flight flight = getFlightById(id);
        flightRepository.delete(flight);
    }

    @Override
    public FlightDTO addPassengerIntoFlight(Integer flightId, Integer passengerId) {
        Flight flight = getFlightById(flightId);
        Set<Passenger> passengers = flight.getPassengers();
        Passenger newPassenger = passengerService.getPassengerById(passengerId);
        if (passengers.contains(newPassenger))
            throw new RuntimeException("Passenger with id: " + passengerId + " already added into flight id: " + flightId);
        passengers.add(newPassenger);
        flightRepository.save(flight);
        FlightDTO flightDTO = convertToFlightDTO(flight);
        return flightDTO;
    }

    @Override
    public FlightDTO addMiscellaneousIntoFlight(Integer flightId, String strMiscellaneous) {
        Flight flight = getFlightById(flightId);
        Miscellaneous miscellaneous = new Miscellaneous();
        miscellaneous.setMiscellaneous(strMiscellaneous);
        Set<Miscellaneous> miscellaneousSet = flight.getMiscellaneous();
        miscellaneousSet.add(miscellaneousService.addMiscellaneous(miscellaneous));
        flightRepository.save(flight);
        FlightDTO flightDTO = convertToFlightDTO(flight);
        return flightDTO;
    }

    @Override
    public List<FlightDTO> getOtherFlight(Integer airlineId) {
        String queryParam = (airlineId == null) ? "" : "?airline.id=" + airlineId;
        List<FlightDTO> flights = webClient.get().uri(otherFlightURL + queryParam)
                    .retrieve()
                    .bodyToFlux(FlightDTO.class)
                    .collectList()
                    .block();
        return flights;
    }

    private FlightDTO convertToFlightDTO(Flight flight) {
        FlightDTO flightDTO = modelMapper
                .map(flight, FlightDTO.class);
        return flightDTO;
    }

}
