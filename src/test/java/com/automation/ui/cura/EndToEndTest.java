package com.automation.ui.cura;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.customexception.CustomException;
import com.automation.helper.PropertyHelper;
import com.automation.selenium.WebDriverHelper;
import com.automation.ui.cura.BasePage;
import com.automation.ui.cura.LoginPage;

public class EndToEndTest {
	WebDriver driver;
	LoginPage loginPage;
	WebDriverHelper webDriverHelper;
	PropertyHelper propertyHelper;
	BasePage basePage;
	HomePage homePage;
	AppointmentPage appointmentPage;
	AppointmentConfirmationPage appointmentConfirmationPage;

	@BeforeClass
	@Parameters({ "runParallel", "environment", "browser", "hubURL" })
	public void setup(String runParallel, String environment, String browser, String hubURL) throws Exception {
		try {
			webDriverHelper = new WebDriverHelper();
			webDriverHelper.initializeDriver(browser, hubURL);
			driver = webDriverHelper.getDriver();
			basePage = new BasePage(driver);
			propertyHelper = new PropertyHelper(environment, "cura");
		} catch (Exception e) {
			throw new CustomException(e);
		}
	}

	@Test
	public void endToEnd() throws Exception {
		try {
			homePage = basePage.navigateToHomePage(propertyHelper.getPropertyValue("url"));
			Assert.assertTrue(homePage.validateThatOnHomePage());
			appointmentPage = homePage.navigateToLoginPage().waitForLoginPageLoad()
					.loginToApp(propertyHelper.getPropertyValue("userName"),
							propertyHelper.getPropertyValue("passowrd"))
					.waitForAppointmentPageLoad();
			appointmentConfirmationPage = appointmentPage
					.makeAnAppointment("Hongkong CURA Healthcare Center", "Yes", "Medicaid", 12, "Morning appointment")
					.waitForAppointmentConfirmationPageLoad();
			Assert.assertEquals(appointmentConfirmationPage.getFacility(), "Hongkong CURA Healthcare Center");
			Assert.assertEquals(appointmentConfirmationPage.getHospitalReadmission(), "Yes");
			Assert.assertEquals(appointmentConfirmationPage.getProgram(), "Medicaid");
			Assert.assertEquals(appointmentConfirmationPage.getVisitDate(), appointmentPage.getDate());
			Assert.assertEquals(appointmentConfirmationPage.getComment(), "Morning appointment");
			homePage=appointmentConfirmationPage.goToHomePage();
			homePage=homePage.Logout();
			//Assert.assertTrue(homePage.checkIfSuccessfullyLogout());
		} catch (Exception e) {
			throw new CustomException(e, driver);
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		basePage.killDriver();
	}
}
