package com.automation.api.herokuapp;

import static org.apache.http.HttpStatus.SC_OK;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.customexception.CustomException;

import io.restassured.response.Response;
import net.datafaker.Faker;

public class HerokuappTest extends BaseApiTest {

	@Test
	public void testTokenIsNotEmpty() throws Exception {
		try {
			Assert.assertTrue(!token.isEmpty());
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testGetAllBooking() throws Exception {
		try {
			Response response = new BookingApi().getAllBooking();
			Assert.assertEquals(response.getStatusCode(), SC_OK);
		} catch (Exception e) {
			throw new CustomException(e);
		}

	}

	@Test
	public void testUpdateBooking() throws Exception {
		try {
			System.out.println(token);
			BookingResponsePojo bookingResponsePojo = new BookingApi().createBooking().as(BookingResponsePojo.class);
			Integer id = bookingResponsePojo.getBookingId();
			BookingPojo bookingPojo = bookingResponsePojo.getBookingPojo();

			Faker faker = new Faker();
			bookingPojo.setFirstname(faker.name().firstName());
			bookingPojo.setLastname(faker.name().lastName());
			bookingPojo.setTotalprice(faker.number().numberBetween(1000, 5000));

			new BookingApi().updateBooking(bookingPojo, id, token);

		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testPartialUpdateBooking() throws Exception {
		try {
			System.out.println(token);
			BookingResponsePojo bookingResponsePojo = new BookingApi().createBooking().as(BookingResponsePojo.class);
			Integer id = bookingResponsePojo.getBookingId();
			BookingPojo bookingPojo = bookingResponsePojo.getBookingPojo();

			Faker faker = new Faker();
			bookingPojo.setTotalprice(faker.number().numberBetween(1000, 5000));

			new BookingApi().partialUpdateBooking(bookingPojo, id, token);

		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void testDeleteBooking() throws Exception {
		try {
			System.out.println(token);
			Integer id = new BookingApi().createBooking().as(BookingResponsePojo.class).getBookingId();
			new BookingApi().deleteBooking(id, token);

		} catch (Exception e) {
			throw new CustomException(e);
		}
	}
}
