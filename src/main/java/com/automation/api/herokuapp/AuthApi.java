package com.automation.api.herokuapp;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;

public class AuthApi extends BaseApi {
	public AuthApi() {
		request.put("BasePath", endPointGenerator.AUTH_ENDPOINT);
	}

	public AuthPojo createAuthPayload() {
		return AuthPojo.builder().username("admin").password("password123").build();
	}

	public Response genrateToken() throws Exception {
		request.put("RequestMethod", "POST");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		request.put("Header", header);
		request.put("Payload", createAuthPayload());
		return processRequest();
	}

	public AuthPojo createAuthPayload(String username, String pasword) {
		return AuthPojo.builder().username(username).password("pasword").build();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new AuthApi().genrateToken().getStatusCode());
	}

}
