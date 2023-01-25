package com.automation.api.gorest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserPojo {
	@JsonProperty("name")
	String name;
	@JsonProperty("gender")
	String gender;
	@JsonProperty("email")
	String email;
	@JsonProperty("status")
	String status;
}
