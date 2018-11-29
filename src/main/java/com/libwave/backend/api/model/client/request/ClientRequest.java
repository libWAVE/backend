package com.libwave.backend.api.model.client.request;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class ClientRequest implements Serializable {

	protected String uuid;

	protected String clientUuid;

	protected String desktopUuid;

	protected Date date = new Date();

	public Date getDate() {
		return date;
	}

	public abstract String getCommand();

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getClientUuid() {
		return clientUuid;
	}

	public void setClientUuid(String clientUuid) {
		this.clientUuid = clientUuid;
	}

	public String getDesktopUuid() {
		return desktopUuid;
	}

	public void setDesktopUuid(String desktopUuid) {
		this.desktopUuid = desktopUuid;
	}

	@Override
	public String toString() {
		return "ClientRequest [uuid=" + uuid + ", clientUuid=" + clientUuid + ", desktopUuid=" + desktopUuid + ", date="
				+ date + "]";
	}

}
