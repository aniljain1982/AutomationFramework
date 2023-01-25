package com.automation.api.gorest;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class UsersApi extends BaseApi {
	public UsersApi() {
		request.put("BasePath", endPointGenerator.USERS_ENDPOINT);
	}

	public CreateUserPojo createUserPayload() {
		Faker faker = new Faker();
		return CreateUserPojo.builder().name(faker.name().fullName()).gender(faker.gender().binaryTypes()).email("xyz12345@gmail.com")
				.status("Active").build();
	}

	public Response getAllUsers() throws Exception {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		request.put("Header", header);
		request.put("RequestMethod", "GET");
		return processRequest();
	}

	public Response validateToken(String token) throws Exception {
		request.put("RequestMethod", "GET");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Bearer " + token);
		request.put("Header", header);
		return processRequest();
	}

	public Response createUser() throws Exception {
		request.put("RequestMethod", "POST");
		Map<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Bearer b5a055ccdc334a53dd86cab08eae0ef4904634587ec8e2244027bb511e360996");
		request.put("Header", header);
		request.put("Payload", createUserPayload());
		return processRequest();
	}
	
	public Response getUserByEmail(String email) throws Exception {
		request.put("RequestMethod", "GET");
		Map<String, String> header=new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Authorization", "Bearer b5a055ccdc334a53dd86cab08eae0ef4904634587ec8e2244027bb511e360996");
		request.put("Header", header);
		Map<String, String> queryParam=new HashMap<>();
		queryParam.put("email", email);
		request.put("QueryParams", queryParam);
		return processRequest();
	}

}
