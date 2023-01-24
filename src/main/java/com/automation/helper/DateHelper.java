package com.automation.helper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public static String returnTodaysDate(String dateFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		return formatter.format(date);
	}

	public static String returnFutureDate(String dateFormat, int days) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return formatter.format(cal.getTime());
	}

	public static String getCurrentDayPlus(int days) {
		LocalDateTime dt = LocalDateTime.from(new Date().toInstant().atZone(ZoneId.of("UTC"))).plusDays(days);
		return Integer.toString(dt.getDayOfMonth());

	}

	public static void main(String[] args) {
		DateHelper.getCurrentDayPlus(5);
	}
}
