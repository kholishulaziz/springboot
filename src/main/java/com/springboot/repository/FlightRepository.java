package com.springboot.repository;

import com.springboot.model.Flight;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query(value = "SELECT * FROM Flight WHERE airline_id = :airlineId ORDER BY flight_id", nativeQuery=true)
    List<Flight> findByAirlineId(Pageable pageable, Integer airlineId);

    List<Flight> findAllByOrderByIdAsc(Pageable pageable);

}
