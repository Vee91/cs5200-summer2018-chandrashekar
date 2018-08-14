package edu.northeastern.cs5200.dto;

import java.util.List;

public class CardWrapper {

	private List<Passenger> passengers;
	private int cardId;
	private FlightSearchResult itinerary;

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public FlightSearchResult getItinerary() {
		return itinerary;
	}

	public void setItinerary(FlightSearchResult itinerary) {
		this.itinerary = itinerary;
	}

}
