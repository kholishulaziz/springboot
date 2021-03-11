package com.springboot.service;

import com.springboot.model.Passenger;
import com.springboot.model.PassengerDTO;
import com.springboot.repository.PassengerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PassengerDTO> getPassenger() {
        List<PassengerDTO> passengers = passengerRepository.findAll().stream().map(this::convertToPassengerDTO)
                .collect(Collectors.toList());
        return passengers;
    }

    @Override
    public Passenger getPassengerById(Integer id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        if (!passenger.isPresent())
            throw new RuntimeException("Passenger with id: " + id + " doesn't exist");
        return passenger.get();
    }

    private PassengerDTO convertToPassengerDTO(Passenger passenger) {
        PassengerDTO passengerDTO = modelMapper
                .map(passenger, PassengerDTO.class);
        return passengerDTO;
    }

}
