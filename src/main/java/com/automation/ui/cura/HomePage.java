package com.automation.ui.cura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	@FindBy(id = "btn-make-appointment")
	WebElement btnMakeAppointment;

	public HomePage(WebDriver driver) {
		super(driver);
		initializeBurgerMenu();
	}

	public boolean validateThatOnHomePage() throws Exception {
		return webElementHelper.checkVisibility(btnMakeAppointment);
	}

	public LoginPage navigateToLoginPage() throws Exception {
		return getBurgerMenu().loadLoginPage();
	}

	public HomePage Logout() throws Exception {
		return getBurgerMenu().logout();
	}

	public boolean checkIfSuccessfullyLogout() throws Exception {
		return getBurgerMenu().checkIfLoginButtonExists();
	}
}
