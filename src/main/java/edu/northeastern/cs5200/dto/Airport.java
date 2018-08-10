package edu.northeastern.cs5200.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Airport {

	private String code;
	private String label;
	private String terminal;

	public Airport() {
		super();
	}

	@JsonCreator
	public Airport(@JsonProperty("airport") String code, @JsonProperty("name") String label,
			@JsonProperty("terminal") String terminal) {
		this.code = code;
		this.label = label;
		this.terminal = terminal;
	}

	public String getCode() {
		return code;
	}

	@JsonSetter("value")
	public void setCode(String code) {
		this.code = code;
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
