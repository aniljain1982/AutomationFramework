package com.automation.api.herokuapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class BookingResponsePojo {
	@JsonProperty("bookingId")
	private int bookingId;

	@JsonProperty("bookingPojo")
	private BookingPojo bookingPojo;
}
