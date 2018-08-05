package edu.northeastern.cs5200.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ResponseResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String uniqueId;

	private String code;

	private String message;

	private List<FieldErrorResource> fieldErrors;

	private Map<String, Object> success;

	private Map<String, Object> errors;

	private Map<String, Object> infoMap;

	public Map<String, Object> getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map<String, Object> infoMap) {
		this.infoMap = infoMap;
	}

	public ResponseResource() {

	}

	public ResponseResource(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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

	public List<FieldErrorResource> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void setFieldErrors(FieldErrorResource fieldError) {
		if (getFieldErrors() == null) {
			this.fieldErrors = new ArrayList<FieldErrorResource>();
		}
		getFieldErrors().add(fieldError);
	}

	public Map<String, Object> getSuccess() {
		return success;
	}

	public void setSuccess(Map<String, Object> success) {
		this.success = success;
	}

	public void setSuccess(String name, Object object) {
		if (this.success == null) {
			this.success = new HashMap<String, Object>();
		}
		getSuccess().put(name, object);
	}

	public Map<String, Object> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}

	public void setErrors(String name, Object errors) {
		if (this.errors == null) {
			this.errors = new HashMap<String, Object>();
		}
		getErrors().put(name, errors);
	}
/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ResponseResource)) {
			return false;
		}
		ResponseResource other = (ResponseResource) obj;
		return equalToUniqueId(other);
	}

	private boolean equalToUniqueId(ResponseResource other) {
		if (uniqueId == null) {
			if (other.uniqueId != null) {
				return false;
			}
		} else if (!uniqueId.equals(other.uniqueId)) {
			return false;
		}
		return true;
	}*/

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ResponseResource [uniqueId=" + uniqueId + ", code=" + code);
		sb.append(", message=" + message + ", fieldErrors=" + fieldErrors);
		sb.append(", success=" + success + ", errors=" + errors + "]");
		return sb.toString();
	}
}