package com.automation.ui.cura;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test {
	
	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println("current date: " + cal.getTime());
		cal.add(Calendar.DATE, 7);
		System.out.println("7 days later: " + cal.getTime());
		System.out.println("----"+ formatter.format(cal.getTime()));
	}

}
