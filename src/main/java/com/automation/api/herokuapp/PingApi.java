package com.automation.api.herokuapp;

import io.restassured.response.Response;

public class PingApi extends BaseApi {

	public PingApi() {
		request.put("BasePath", endPointGenerator.PING_ENDPOINT);
	}

	public Response healthCheck() throws Exception {
		request.put("RequestMethod", "GET");
		return processRequest();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new PingApi().healthCheck().getStatusCode());
	}
}
