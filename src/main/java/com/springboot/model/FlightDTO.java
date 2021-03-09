package com.springboot.model;

import java.util.Date;
import java.util.Set;

public class FlightDTO {

    private int id;
    private String flightCode;
    private String origin;
    private String destination;
    private Date departureDate;
    private Airline airline;
    private Set<Passenger> passengers;
    private Set<Miscellaneous> miscellaneous;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Set<Miscellaneous> getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Set<Miscellaneous> miscellaneous) {
        this.miscellaneous = miscellaneous;
    }
}
