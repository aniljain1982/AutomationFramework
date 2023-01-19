package com.automation.api.gorest;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.restassured.RestAssuredProcessor;

import io.restassured.response.Response;

public class GoRestTest02 {
	Map<String, Object> request;
	
	@BeforeMethod
	public void setup() {
		request=new HashMap<>();
		request.put(null, request);
		request.put("BaseUri", "https://gorest.co.in/public/v2");
		request.put("BasePath", "users");
	}
	@Test
	public void validToken() throws Exception{
		request.put("RequestMethod", "GET");
		Map<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Bearer b5a055ccdc334a53dd86cab08eae0ef4904634587ec8e2244027bb511e360996");
		request.put("Header", header);
		
		RestAssuredProcessor processor=new RestAssuredProcessor();
		processor.processRestRequest(request);
		Response response=processor.getResponse();
		Assert.assertEquals(response.getStatusCode(), 200, "Valid token");
		Reporter.log("HTTP Status Code: " + response.getStatusCode(),true);
	}
	
	@Test
	public void invalidToken() throws Exception{
		request.put("RequestMethod", "GET");
		Map<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "invalidToken");
		request.put("Header", header);
		
		RestAssuredProcessor processor=new RestAssuredProcessor();
		processor.processRestRequest(request);
		Response response=processor.getResponse();
		Assert.assertEquals(response.getStatusCode(), 401, "Invalid token");
		Reporter.log("HTTP Status Code: " + response.getStatusCode(),true);
	}
	
	@AfterMethod
	public void cleanup() {
		request=null;
	}
}
