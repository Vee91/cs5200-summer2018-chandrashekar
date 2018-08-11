package edu.northeastern.cs5200.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm")
	private Date departsAt;
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm")
	private Date arrivesAt;
	private Airport origin;
	private Airport destination;
	private String airline;
	private int flightNumber;
	private String aircraft;
	private String operatingAirlineName;
	private String aircraftName;
	
	
	public String getAircraftName() {
		return aircraftName;
	}

	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}

	public String getOperatingAirlineName() {
		return operatingAirlineName;
	}

	public void setOperatingAirlineName(String operatingAirlineName) {
		this.operatingAirlineName = operatingAirlineName;
	}

	public Flight() {}

	public Flight(Date departsAt, Date arrivesAt, Airport origin, Airport destination, String airline,
			int flightNumber, String aircraft) {
		super();
		this.departsAt = departsAt;
		this.arrivesAt = arrivesAt;
		this.origin = origin;
		this.destination = destination;
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.aircraft = aircraft;
	}

	public Date getDepartsAt() {
		return departsAt;
	}

	@JsonSetter("departs_at")
	public void setDepartsAt(Date departsAt) {
		this.departsAt = departsAt;
	}

	public Date getArrivesAt() {
		return arrivesAt;
	}

	@JsonSetter("arrives_at")
	public void setArrivesAt(Date arrivesAt) {
		this.arrivesAt = arrivesAt;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	@JsonSetter("operating_airline")
	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	@JsonSetter("flight_number")
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

}
