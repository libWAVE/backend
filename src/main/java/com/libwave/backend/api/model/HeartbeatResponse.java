package com.libwave.backend.api.model;

@SuppressWarnings("serial")
public class HeartbeatResponse extends BaseModelObject {

	public HeartbeatResponse() {
		super();
	}

	@Override
	public String getType() {
		return "ping";
	}

	@Override
	public String toString() {
		return "PingResponse [getType()=" + getType() + "]";
	}

}