package com.automation.api.herokuapp;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.automation.customexception.CustomException;

import io.restassured.response.Response;

public class BaseApiTest {
	protected String token;

	@BeforeSuite
	public void testHealthCheck() throws Exception {
		try {
			Assert.assertEquals(new PingApi().healthCheck().getStatusCode(), 201);
		} catch (Exception e) {
			throw new CustomException(e);
		}

	}

	@BeforeTest
	public void testCreateTokenStatusOK() throws Exception {
		try {
			Response response = new AuthApi().genrateToken();
			token = response.as(AuthResponsePojo.class).getToken();
			Assert.assertEquals(response.getStatusCode(), 200);
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

}
