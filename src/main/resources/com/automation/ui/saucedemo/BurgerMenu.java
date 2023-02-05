package com.automation.ui.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BurgerMenu extends BasePage {

	@FindBy(id = "react-burger-menu-btn")
	WebElement btnBurgerMenu;

	@FindBy(id = "logout_sidebar_link")
	WebElement btnLogout;
	
	@FindBy(id = "react-burger-cross-btn")
	WebElement btnCross;
	
	@FindBy(id = "inventory_sidebar_link")
	WebElement btnAllItems;
	
	@FindBy(id = "about_sidebar_link")
	WebElement btnAbout;
	
	@FindBy(id = "reset_sidebar_link")
	WebElement btnResetAppState;

	public BurgerMenu(WebDriver driver) {
		super(driver);
	}

	public LoginPage logout() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnBurgerMenu).click(btnBurgerMenu)
				.waitTillElementIsVisible(btnLogout).click(btnLogout);
		return initializeLoginPage();
	}
	
	public void closeBurgerMenu() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnBurgerMenu).click(btnBurgerMenu)
				.waitTillElementIsVisible(btnCross).click(btnLogout);
	}

}
