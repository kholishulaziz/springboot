package com.springboot.repository;

import com.springboot.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends ElasticsearchRepository<Flight, String> {

    List<Flight> findByAirlineId(Pageable pageable, Integer airlineId);

    List<Flight> findByFlightCodeContaining(Pageable pageable, String flightCode);

    List<Flight> findByAirlineIdAndFlightCode (Pageable pageable, Integer airlineId, String flightCode);

    Page<Flight> findAll(Pageable pageable);

}
