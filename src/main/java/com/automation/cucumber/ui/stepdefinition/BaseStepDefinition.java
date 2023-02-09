package com.automation.cucumber.ui.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.xml.XmlTest;

import com.automation.selenium.WebDriverHelper;
import com.automation.ui.saucedemo.BasePage;

public class BaseStepDefinition {
	WebDriver driver;
	WebDriverHelper webDriverHelper;
	BasePage basePage;
	XmlTest xmlTest;

	public BaseStepDefinition() {
		try {
			xmlTest = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
			webDriverHelper = new WebDriverHelper();
			webDriverHelper.initializeDriver(xmlTest.getParameter("browser"), xmlTest.getParameter("runParallel"));
			driver = webDriverHelper.getDriver();
			basePage = new BasePage(driver);
			System.out.println(xmlTest.getParameter("runParallel"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
