package com.automation.api.herokuapp;

import java.util.HashMap;
import java.util.Map;

import com.automation.restassured.RestAssuredProcessor;

import io.restassured.response.Response;

public class BaseApi {
	protected Map<String, Object> request;
	public EndPointGenerator endPointGenerator;

	public BaseApi() {
		request = new HashMap<String, Object>();
		endPointGenerator = new EndPointGenerator("https://restful-booker.herokuapp.com");
		request.put("BaseUri", endPointGenerator.getBaseUrl());
	}
	
	public Response processRequest() throws Exception{
		RestAssuredProcessor processor = new RestAssuredProcessor();
		processor.processRestRequest(request);
		return processor.getResponse();
	}

}
