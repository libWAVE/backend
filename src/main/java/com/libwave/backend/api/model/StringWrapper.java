package com.libwave.backend.api.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StringWrapper implements Serializable {

	private String value;

	public StringWrapper() {
		super();
	}

	public StringWrapper(String v) {
		super();
		this.value = v;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StringWrapper [value=" + value + "]";
	}

}
