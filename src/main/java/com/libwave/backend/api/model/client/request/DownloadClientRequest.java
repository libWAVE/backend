package com.libwave.backend.api.model.client.request;

@SuppressWarnings("serial")
public class DownloadClientRequest extends ClientRequest {

	private String uiud;

	public String getUiud() {
		return uiud;
	}

	public void setUiud(String uiud) {
		this.uiud = uiud;
	}

	@Override
	public String getCommand() {
		return "download-request";
	}

}
