package com.automation.api.gorest;

public class EndPointGenerator {
	public final String USERS_ENDPOINT = "users";
	public final String VERSION="/v2";
	private String BASE_URL;

	public EndPointGenerator(String baseUrl) {
		this.BASE_URL = baseUrl + VERSION;
	}

	public String getBaseUrl() {
		return BASE_URL;
	}
}
