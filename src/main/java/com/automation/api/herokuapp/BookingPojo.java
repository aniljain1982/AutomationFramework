package com.automation.api.herokuapp;

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
public class BookingPojo {
	@JsonProperty("firstname")
	public String firstname;
	@JsonProperty("lastname")
	public String lastname;
	@JsonProperty("totalprice")
	public int totalprice;
	@JsonProperty("depositpaid")
	public boolean depositpaid;
	@JsonProperty("bookingDates")
	public BookingDatesPojo bookingDates;
	@JsonProperty("additionalneeds")
	public String additionalneeds;
}
