package edu.northeastern.cs5200.dto;

public class FlightSearchResult {

	private Itinerary outbound;
	private Itinerary inbound;
	private String price;
	private String tax;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

}
