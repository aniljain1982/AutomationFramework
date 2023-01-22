package com.automation.ui.cura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(name = "username")
	WebElement txtUserName;

	@FindBy(name = "password")
	WebElement txtPassword;

	@FindBy(id = "btn-login")
	WebElement btnLogin;

	public AppointmentPage loginToApp(String userName, String password) throws Exception {
		webElementHelper.waitTillElementIsVisible(txtUserName).clear(txtUserName).clear(txtPassword);
		txtUserName.sendKeys(userName);
		txtPassword.sendKeys(password);
		btnLogin.click();
		return initializeAppointmentPage();
	}
	
	public LoginPage waitForLoginPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnLogin).waitTillElementIsClickable(btnLogin);
		return initializeLoginPage();
	}

	public boolean validateThatOnLoginPage() throws Exception{
		return webElementHelper.checkVisibility(btnLogin);
	}

}
