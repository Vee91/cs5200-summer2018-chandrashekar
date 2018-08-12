package edu.northeastern.cs5200.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookedFlight {

	private String flight;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm")
	private Date departure;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm")
	private Date arrival;
	private String status;
	private String airline;
	private String aircraft;
	private String origin;
	private String originTerminal;
	private String destination;
	private String destinationTerminal;
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getOriginTerminal() {
		return originTerminal;
	}
	public void setOriginTerminal(String originTerminal) {
		this.originTerminal = originTerminal;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDestinationTerminal() {
		return destinationTerminal;
	}
	public void setDestinationTerminal(String destinationTerminal) {
		this.destinationTerminal = destinationTerminal;
	}
	
	
}
