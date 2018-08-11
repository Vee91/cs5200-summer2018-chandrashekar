package edu.northeastern.cs5200.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Airport {

	private String value;
	private String label;
	private String terminal;

	public Airport() {
		super();
	}

	public Airport(String value, String label, String terminal) {
		this.value = value;
		this.label = label;
		this.terminal = terminal;
	}

	public String getValue() {
		return value;
	}

	@JsonSetter("airport")
	public void setAirport(String value) {
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

}
