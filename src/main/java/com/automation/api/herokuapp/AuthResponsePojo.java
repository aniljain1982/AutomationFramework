package com.automation.api.herokuapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class AuthResponsePojo {
	@JsonProperty
	private String token;
}
