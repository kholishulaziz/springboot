package com.springboot.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "flight")
public class Flight {

	@ApiModelProperty(required = false, hidden = true)
	@Id
	private String id;

	@Field(type = FieldType.Keyword)
	@ApiModelProperty(notes = "Unique flight code", example = "GA303")
	private String flightCode;

	@ApiModelProperty(example = "SUB")
	private String origin;

	@ApiModelProperty(example = "JOG")
	private String destination;

	@Field(name = "departure_date", type = FieldType.Date, format = DateFormat.date_time)
	private Date departureDate;

	@ApiModelProperty(notes = "Airline", example = "1")
	private int airlineId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
