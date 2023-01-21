package com.automation.ui.saucedemo;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.customexception.CustomException;
import com.automation.helper.PropertyHelper;
import com.automation.selenium.WebDriverHelper;

public class Login {
	WebDriver driver;

	BasePage basePage;
	LoginPage loginPage;
	ProductPage productPage;

	WebDriverHelper webDriverHelper;
	PropertyHelper propertyHelper;

	@BeforeClass
	@Parameters({ "runParallel", "environment", "browser", "hubURL" })
	public void setup(String runParallel, String environment, String browser, String hubURL) throws Exception {
		webDriverHelper = new WebDriverHelper();
		webDriverHelper.initializeDriver(browser, runParallel);
		driver = webDriverHelper.getDriver();
		basePage = new BasePage(driver);
		propertyHelper = new PropertyHelper(environment, "saucedemo");
	}

	/**
	 * Valid Credentials
	 */
	@Test
	public void validCredentials() throws Exception{
		try {
			// Login page
			loginPage = basePage.navigateToLoginPage(propertyHelper.getPropertyValue("sauceUrl"));
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
			productPage = loginPage.waitForLoginPageLoad().loginToApp(propertyHelper.getPropertyValue("sauceUserName"),
					propertyHelper.getPropertyValue("saucePassowrd")).waitForProductPageLoad();
			Assert.assertTrue(productPage.validateThatOnProductPage());
		} catch (Exception e) {
			throw new CustomException(e, driver);
		}
	}

	/**
	 * invalid Credentials
	 */
	@Test
	public void invalidCredentials() throws Exception{
		try {
			// Login page
			loginPage = basePage.navigateToLoginPage(propertyHelper.getPropertyValue("sauceUrl"));
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
			loginPage.waitForLoginPageLoad().loginToApp(propertyHelper.getPropertyValue("sauceUserName"),
					"invalidPassword");
			Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
		} catch (Exception e) {
			throw new CustomException(e, driver);
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		basePage.killDriver();
	}
}
