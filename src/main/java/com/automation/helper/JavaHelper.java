package com.automation.helper;

import java.time.Instant;

public class JavaHelper {
	public static long generateRandomNumber() {
		return Instant.now().toEpochMilli();
	}
}
