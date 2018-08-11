package edu.northeastern.cs5200.dto;

public class FlightSearchResult {

	private Itinerary outbound;
	private Itinerary inbound;
	private float price;
	private float tax;

	public Itinerary getOutbound() {
		return outbound;
	}

	public void setOutbound(Itinerary outbound) {
		this.outbound = outbound;
	}

	public Itinerary getInbound() {
		return inbound;
	}

	public void setInbound(Itinerary inbound) {
		this.inbound = inbound;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

}
