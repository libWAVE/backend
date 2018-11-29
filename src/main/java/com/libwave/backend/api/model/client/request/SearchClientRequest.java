package com.libwave.backend.api.model.client.request;

@SuppressWarnings("serial")
public class SearchClientRequest extends ClientRequest {

	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String getCommand() {
		return "search-request";
	}

}
