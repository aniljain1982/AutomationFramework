package com.automation.cucumber.ui.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.selenium.WebDriverHelper;
import com.automation.ui.saucedemo.BasePage;
import com.automation.ui.saucedemo.LoginPage;
import com.automation.ui.saucedemo.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoStepDefinition {
	WebDriver driver;
	WebDriverHelper webDriverHelper;

	BasePage basePage;
	LoginPage loginPage;
	ProductPage productPage;

	@Before
	public void setup() throws Exception {
		webDriverHelper = new WebDriverHelper();
		webDriverHelper.initializeDriver("Chrome", "false");
		driver = webDriverHelper.getDriver();
		basePage = new BasePage(driver);
	}

	@After
	public void tearDown() throws Exception {
		basePage.killDriver();
	}

	@Given("^User is on login page$")
	public void user_is_on_login_page() throws Exception {
		loginPage = basePage.navigateToLoginPage("https://www.saucedemo.com/").waitForLoginPageLoad();
	}

	@When("The user logins to app with username as {string} and password as {string}")
	public void the_user_logins_to_app_with_username_as_and_password_as(String username, String password)
			throws Exception {
		productPage = loginPage.loginToApp(username, password).waitForProductPageLoad();
	}

	@Then("The user should login successfully and is brought to the inventory page")
	public void the_user_should_login_successfully_and_is_brought_to_the_inventory_page() throws Exception {
		Assert.assertTrue(productPage.validateThatOnProductPage());
	}

}
