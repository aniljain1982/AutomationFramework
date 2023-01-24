package com.automation.api.herokuapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDatesPojo {
	@JsonProperty
	public String checkin;
	@JsonProperty
	public String checkout;
}
