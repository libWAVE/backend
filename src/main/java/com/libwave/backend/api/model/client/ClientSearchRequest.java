package com.libwave.backend.api.model.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientSearchRequest implements Serializable {

	private String uuid;

	private String query;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "ClientSearchRequest [uuid=" + uuid + ", query=" + query + "]";
	}

}
