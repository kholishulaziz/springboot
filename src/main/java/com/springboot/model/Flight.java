package com.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "flight")
public class Flight {

	@ApiModelProperty(required = false, hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flight_id")
	private int id;

	@NotBlank
	@ApiModelProperty(notes = "Unique flight code", example = "GA303")
	private String flightCode;

	@ApiModelProperty(example = "SUB")
	private String origin;

	@ApiModelProperty(example = "JOG")
	private String destination;

	private Date departureDate;

	@Transient
	@ApiModelProperty(notes = "Airline", example = "1")
	private int airlineId;

	@JsonIgnore
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "airline_id")
	private Airline airline;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = PERSIST)
	@JoinTable(
			name = "flight_passenger",
			joinColumns = {@JoinColumn(name = "flight_id")},
			inverseJoinColumns = {@JoinColumn(name = "passenger_id")}
	)
	private Set<Passenger> passengers;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JoinColumn(name="flight_id")
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

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
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
