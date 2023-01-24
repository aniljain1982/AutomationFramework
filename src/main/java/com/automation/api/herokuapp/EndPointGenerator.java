package com.automation.api.herokuapp;

public class EndPointGenerator {
	public final String AUTH_ENDPOINT = "auth";
	public final String BOOKING_ENDPOINT = "booking";
	public final String PING_ENDPOINT = "ping";
	private String BASE_URL;

	public EndPointGenerator(String baseUrl) {
		this.BASE_URL = baseUrl;
	}

	public String getBaseUrl() {
		return BASE_URL;
	}
}
