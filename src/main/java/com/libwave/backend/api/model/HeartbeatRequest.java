package com.libwave.backend.api.model;

import java.io.Serializable;

/**
 * Client : send command, receive notifications
 */
@SuppressWarnings("serial")
public class HeartbeatRequest implements Serializable {

	private String command;

	private String uuid;

	public HeartbeatRequest() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return "HeartbeatRequest [command=" + command + ", uuid=" + uuid + "]";
	}

}