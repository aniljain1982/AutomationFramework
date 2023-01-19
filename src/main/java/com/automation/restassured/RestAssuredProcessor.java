package com.automation.restassured;

import java.util.Arrays;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RestAssuredProcessor {
	private Response response;
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public void processRestRequest(Map<String, Object> requestMap) throws Exception {
		Response response = null;
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		String[] supportedApiMethod = { "POST", "GET", "DELETE", "PUT" };
		
		if(!requestMap.containsKey("BaseUri")) {
			throw new Exception("Request map does not have Base Uri");
		}
		if(!requestMap.containsKey("BasePath")) {
			throw new Exception("Request map does not have Base Path");
		}
		if(!requestMap.containsKey("RequestMethod")) {
			throw new Exception("Request map does not have Request Method");
		}
		
		String requestMethod = requestMap.get("RequestMethod").toString().toUpperCase();
		if (!Arrays.asList(supportedApiMethod).contains(requestMethod)) {
			throw new Exception("Unsopported Api method: " + requestMethod);
		}
		
		requestSpecBuilder.setBaseUri(requestMap.get("BaseUri").toString());
		requestSpecBuilder.setBasePath(requestMap.get("BasePath").toString());

		if (requestMap.containsKey("Header")) {
			requestSpecBuilder.addHeaders((Map) requestMap.get("Header"));
		}
		if (requestMap.containsKey("PathParams")) {
			requestSpecBuilder.addPathParams((Map) requestMap.get("PathParams"));
		}
		if (requestMap.containsKey("QueryParams")) {
			requestSpecBuilder.addQueryParams((Map) requestMap.get("QueryParams"));
		}
		if (requestMap.containsKey("Payload")) {
			requestSpecBuilder.setBody(requestMap.get("Payload"));
		}

		RequestSpecification requestSpec = requestSpecBuilder.build();
		QueryableRequestSpecification queryRequest = SpecificationQuerier.query(requestSpec);
		System.out.println("Base URI: " + queryRequest.getBaseUri());
		System.out.println("Base Path: " + queryRequest.getBasePath());
		System.out.println("Method: " + queryRequest.getMethod());
		System.out.println("Herader: " + queryRequest.getHeaders());
		System.out.println("Path Path: " + queryRequest.getPathParams());
		System.out.println("Query Param: " + queryRequest.getQueryParams());
		System.out.println("Form Param: " + queryRequest.getFormParams());
		System.out.println("MultiPart Param: " + queryRequest.getMultiPartParams());
		System.out.println("Body: " + queryRequest.getBody());

		switch (requestMethod) {
		case "GET": {
			response = RestAssured.expect().given().spec(requestSpec).get();
			break;
		}

		case "POST": {
			response = RestAssured.expect().given().spec(requestSpec).post();
			break;
		}
		case "DELETE": {
			response = RestAssured.expect().given().spec(requestSpec).delete();
			break;
		}
		case "PUT": {
			response = RestAssured.expect().given().spec(requestSpec).put();
			break;
		}
		}
		setResponse(response);
	}

}
