package edu.northeastern.cs5200.dto;

import java.util.List;

public class Wrapper {
	
	private List<Passenger> passengers;
	private CreditCard card;
	private FlightSearchResult itinerary;
	
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public CreditCard getCard() {
		return card;
	}
	public void setCard(CreditCard card) {
		this.card = card;
	}
	public FlightSearchResult getItinerary() {
		return itinerary;
	}
	public void setItinerary(FlightSearchResult itinerary) {
		this.itinerary = itinerary;
	}
	
	

}
