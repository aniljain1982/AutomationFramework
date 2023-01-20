package com.automation.customexception;

import java.io.FileOutputStream;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automation.helper.JavaHelper;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	WebDriver driver;

	public CustomException() {
		super();
	}

	public CustomException(Exception e) throws Exception {
		String exceptionClass = getExceptionClass(e);

		switch (exceptionClass) {
		case "java.lang":
		case "java.io":
			throw new Exception("Exception: " + e.getMessage() + "<br>Issue Type: Script Issue <br>ExceptionTrace:"
					+ e.getStackTrace());
		case "selenium.common.exceptions":
		case "org.openqa.selenium":
		case "com.automation.customexception":
			throw new Exception("Exception: " + e.getMessage() + "<br>Issue Type: Application Issue <br>ExceptionTrace:"
					+ e.getStackTrace());
		default:
			throw new Exception("Exception: " + e.getMessage() + "<br>Issue Type: Error Occurred <br>ExceptionTrace:"
					+ e.getStackTrace());
		}
	}

	public CustomException(Exception e, WebDriver driver) throws Exception {
		this.driver = driver;
		String exceptionClass = getExceptionClass(e);
		if (driver != null) {
			switch (exceptionClass) {
			case "java.lang":
			case "java.io":
				throw new Exception("Exception: " + e.getMessage() + "<br>Issue Type: Script Issue <br>ExceptionTrace:"
						+ e.getStackTrace());
			case "selenium.common.exceptions":
			case "org.openqa.selenium":
			case "com.automation.customexception":
				throw new Exception(
						"Exception: " + e.getMessage() + "<br>Issue Type: Application Issue <br>ExceptionTrace:"
								+ e.getStackTrace() + "<br>Screenshot:<br>" + takeSceentShot(driver));
			default:
				throw new Exception("Exception: " + e.getMessage()
						+ "<br>Issue Type: Error Occurred <br>ExceptionTrace:" + e.getStackTrace());
			}
		} else {

		}

	}

	public String takeSceentShot(WebDriver driver) {
		String destFile = "";
		try {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
			byte[] byteArr = Base64.getDecoder().decode(base64code);
			destFile = "screenshot" + JavaHelper.generateRandomNumber() + ".png";
			FileOutputStream fos = new FileOutputStream(destFile);
			fos.write(byteArr);
			fos.close();
			System.out.println("Screenshot saved successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return destFile;

	}

	public String getExceptionClass(Exception e) {
		return e.getClass().getCanonicalName().substring(0, e.getClass().getCanonicalName().lastIndexOf("."));
	}
}
