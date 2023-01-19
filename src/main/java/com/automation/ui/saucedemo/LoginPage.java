package com.automation.ui.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.selenium.WebElementHelper;

public class LoginPage extends BasePage {

	@FindBy(id = "user-name")
	WebElement txtUserName;

	@FindBy(id = "password")
	WebElement txtPassword;

	@FindBy(id = "login-button")
	WebElement btnLogin;

	public LoginPage(WebDriver driver) {
		super(driver);
		WebElementHelper.setWaitTime(60);
	}

	public LoginPage waitForLoginPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnLogin).waitTillElementIsClickable(btnLogin);
		return initializeLoginPage();
	}

	public boolean validateThatOnLoginPage() {
		return webElementHelper.checkVisibility(btnLogin);
	}

	public ProductPage loginToApp(String userName, String password) throws Exception {
		webElementHelper.waitTillElementIsVisible(txtUserName).clear(txtUserName).clear(txtPassword);
		txtUserName.sendKeys(userName);
		txtPassword.sendKeys(password);
		btnLogin.click();
		return initializeProductPage();
	}
}
