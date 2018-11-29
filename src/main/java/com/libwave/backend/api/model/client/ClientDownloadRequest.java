package com.libwave.backend.api.model.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientDownloadRequest implements Serializable {

	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "ClientDownloadRequest [uuid=" + uuid + "]";
	}

}
