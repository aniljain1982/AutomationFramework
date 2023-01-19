package com.automation.api.gorest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.restassured.RestAssuredProcessor;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GoRestTest01 {
	Map<String, Object> request;
	@BeforeMethod
	public void setup() {
		request=new HashMap<String, Object>();
		request.put("BaseUri", "https://gorest.co.in/public/v2");
		request.put("BasePath", "users");
		Map<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Bearer b5a055ccdc334a53dd86cab08eae0ef4904634587ec8e2244027bb511e360996");
		request.put("Header", header);
	}
	
	@Test
	public void getAllUsers() throws Exception{
		request.put("RequestMethod", "GET");
		RestAssuredProcessor processor=new RestAssuredProcessor();
		processor.processRestRequest(request);
		Response response=processor.getResponse();
		JsonPath jsonPathEvaluator = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 200, "Get All user");
		Reporter.log("HTTP Status Code: " + response.getStatusCode() + "," + jsonPathEvaluator.get("name"),true);
	}
	
	@Test
	public void createUser() throws Exception{
		request.put("RequestMethod","POST");
		CreateUserPojo user=new CreateUserPojo();
		user.setName("Anil");
		user.setGender("Male");
		user.setEmail("anil4242@gmail.com");
		user.setStatus("Active");
		request.put("Payload", user);
		
		RestAssuredProcessor processor=new RestAssuredProcessor();
		processor.processRestRequest(request);
		Response response=processor.getResponse();
		JsonPath jsonPathEvaluator = response.jsonPath();
		Reporter.log("HTTP Status Code: " + response.getStatusCode() + "," + response.getBody().asPrettyString(),true);
		Assert.assertEquals(response.getStatusCode(), 201, "createUser");	
	}
	
	/*@Test
	public void updateUser() throws Exception {
		System.out.println("********************");
		request.put("RequestMethod","GET");
		Map<String, String> queryParam=new HashMap<>();
		queryParam.put("email", "anil4242@gmail.com");
		request.put("QueryParams", queryParam);
		Response response=new RestAssuredProcessor().processRestRequest(request);
		System.out.println(response.statusCode());
		JsonPath jsonPathEvaluator = response.jsonPath();
		String id=jsonPathEvaluator.getString("id");
		System.out.println("id:" + id);
		request=null;
		setup();
		request.put("RequestMethod","PUT");
		Map<String, String> pathParams=new HashMap<>();
		pathParams.put("id",id);
		request.put("PathParams", pathParams);
		CreateUserPojo user=new CreateUserPojo();
		user.setName("Sunil");
		user.setGender("Male");
		user.setEmail("anil4242@gmail.com");
		user.setStatus("Active");
		request.put("Payload", user);
		response=new RestAssuredProcessor().processRestRequest(request);
		System.out.println(response.statusCode());
		System.out.println(response.getBody().asString());
		System.out.println("********************");
	}*/
	
	@Test
	public void getUserByEmail() throws Exception  {
		request.put("RequestMethod","GET");
		Map<String, String> queryParam=new HashMap<>();
		queryParam.put("email", "anil4242@gmail.com");
		request.put("QueryParams", queryParam);
		RestAssuredProcessor processor=new RestAssuredProcessor();
		processor.processRestRequest(request);
		Response response=processor.getResponse();
		JsonPath jsonPathEvaluator = response.jsonPath();
		Assert.assertEquals(response.getStatusCode(), 200, "getUserByEmail");
		Reporter.log("HTTP Status Code: " + response.getStatusCode() + "," + response.getBody().asPrettyString(),true);
	}
	
	@AfterMethod
	public void cleanup() {
		request=null;
	}
}
