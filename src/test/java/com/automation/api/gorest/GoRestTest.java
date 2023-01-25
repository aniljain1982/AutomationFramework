package com.automation.api.gorest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.automation.customexception.CustomException;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GoRestTest {
	@Test
	public void testGetAllUsers() throws Exception {
		try {
			Response response = new UsersApi().getAllUsers();
			JsonPath jsonPathEvaluator = response.jsonPath();
			Assert.assertEquals(response.getStatusCode(), 200, "Get All user");
			Reporter.log("HTTP Status Code: " + response.getStatusCode() + "," + jsonPathEvaluator.get("name"), true);
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testValidToken() throws Exception {
		try {
			Response response = new UsersApi()
					.validateToken("b5a055ccdc334a53dd86cab08eae0ef4904634587ec8e2244027bb511e360996");
			Assert.assertEquals(response.getStatusCode(), 200, "Valid token");
			Reporter.log("HTTP Status Code: " + response.getStatusCode(), true);
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testInvalidToken() throws Exception {
		try {
			Response response = new UsersApi().validateToken("invalid");
			Assert.assertEquals(response.getStatusCode(), 401, "Invalid token");
			Reporter.log("HTTP Status Code: " + response.getStatusCode(), true);
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testCreateUser() throws Exception {
		try {
			Response response = new UsersApi().createUser();
			JsonPath jsonPathEvaluator = response.jsonPath();
			Reporter.log("HTTP Status Code: " + response.getStatusCode() + "," + response.getBody().asPrettyString(),
					true);
			Assert.assertEquals(response.getStatusCode(), 201, "createUser");
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testGetUserByMail() throws Exception {
		try {
			Response response = new UsersApi().getUserByEmail("xyz12345@gmail.com");
			JsonPath jsonPathEvaluator = response.jsonPath();
			Assert.assertEquals(response.getStatusCode(), 200, "getUserByEmail");
			Reporter.log("HTTP Status Code: " + response.getStatusCode() + "," + response.getBody().asPrettyString(),
					true);
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}
}
