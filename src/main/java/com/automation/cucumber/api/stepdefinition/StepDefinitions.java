package com.automation.cucumber.api.stepdefinition;

import org.testng.Assert;

import com.automation.api.herokuapp.AuthPojo;
import com.automation.api.herokuapp.BaseApi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class StepDefinitions extends BaseApi{
    Response response = null;
      
    @Given("Get the API {string}")
    public void get_the_api(String path) {
    	request.put("BasePath", path);
    }

    @When("Make a Rest Call with {string} method")
    public void make_a_rest_call_with_method(String method) throws Exception{
    	  request.put("RequestMethod", method);
          response=processRequest();
          System.out.println("************ Response Body Start *****************");
          response.getBody().print();
          System.out.println("************ Response Body End *******************");
    }

    @Then("Verify if the status code is {string}")
    public void verify_if_the_status_code_is(String statusCode) {
    	System.out.println("Status code : " + response.getStatusCode());
    	Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Given("Create a AuthPojo with {string} and {string}")
    public void create_a_auth_pojo_with_and(String username, String password) {
    	request.put("Payload", AuthPojo.builder().username(username).password(password).build());
    }
    
}
