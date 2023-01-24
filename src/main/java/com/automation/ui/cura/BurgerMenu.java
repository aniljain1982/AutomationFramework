package com.automation.ui.cura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BurgerMenu extends BasePage {

	public BurgerMenu(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "menu-toggle")
	WebElement menu;

	@FindBy(xpath = "//a[text()='Login']")
	WebElement lnkLogin;

	@FindBy(xpath = "//a[text()='Logout']")
	WebElement lnkLogout;

	public LoginPage loadLoginPage() throws Exception {
		webElementHelper.waitTillElementIsVisible(menu).click(menu).waitTillElementIsVisible(lnkLogin).click(lnkLogin);
		return initializeLoginPage();
	}

	public HomePage logout() throws Exception {
		webElementHelper.waitTillElementIsVisible(menu).click(menu).waitTillElementIsVisible(lnkLogout)
				.click(lnkLogout);
		return initializeHomePage();
	}

	public boolean checkIfLoginButtonExists() throws Exception{
		return webElementHelper.checkPresence(By.id("//a[text()='Login']"));
	}
}
