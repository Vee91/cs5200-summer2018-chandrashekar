package edu.northeastern.cs5200.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class FieldErrorResource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String uniqueId;
	
	private String resource;
	
	private String field;
	
	private String code;
	
	private String message;
	
	private String type;
	
	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FieldErrorResource [resource=" + resource + ", field=" + field
				+ ", code=" + code + ", message=" + message + ", type=" + type
				+ "]";
	}
}
