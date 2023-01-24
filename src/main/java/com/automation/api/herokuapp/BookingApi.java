package com.automation.api.herokuapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class BookingApi extends BaseApi {
	public BookingApi() {
		request.put("BasePath", endPointGenerator.BOOKING_ENDPOINT);
	}

	public BookingPojo createBookingPayload() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(new Date());

		Faker faker = new Faker();

		return BookingPojo.builder().firstname(faker.name().firstName()).lastname(faker.name().lastName())
				.totalprice(faker.number().numberBetween(1000, 5000)).depositpaid(true)
				.bookingDates(BookingDatesPojo.builder().checkin(date).checkout(date).build())
				.additionalneeds("Breakfast and dinner").build();
	}

	public Response getAllBooking() throws Exception {
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		request.put("Header", header);
		request.put("RequestMethod", "GET");
		return processRequest();
	}

	public Response getBookingById(String id) throws Exception {
		request.put("RequestMethod", "GET");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		request.put("Header", header);
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		request.put("Params", params);
		return processRequest();
	}

	public Response createBooking() throws Exception {
		request.put("RequestMethod", "POST");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		request.put("Header", header);
		request.put("Payload", createBookingPayload());
		return processRequest();
	}

	public Response updateBooking(BookingPojo bookingPojo, Integer bookingId, String token) throws Exception {
		request.put("RequestMethod", "put");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		header.put("Cookie", "token=" + token);
		request.put("Header", header);
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", bookingId);
		request.put("Params", params);
		request.put("Payload", bookingPojo);
		return processRequest();
	}

	public Response partialUpdateBooking(BookingPojo bookingPojo, Integer bookingId, String token) throws Exception {
		request.put("RequestMethod", "PATCH");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		header.put("Cookie", "token=" + token);
		request.put("Header", header);
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", bookingId);
		request.put("Params", params);
		request.put("Payload", bookingPojo);
		return processRequest();
	}

	public Response deleteBooking(Integer bookingId, String token) throws Exception {
		request.put("RequestMethod", "DELETE");
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		header.put("Cookie", "token=" + token);
		request.put("Header", header);
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", bookingId);
		request.put("Params", params);
		return processRequest();
	}

	public static void main(String[] args) throws Exception {
		Response res = new BookingApi().createBooking();
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody());
	}

}
