package com.automation.ui.cura;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium.WebElementHelper;

public class BasePage {
	public WebDriver driver;
	public WebElementHelper webElementHelper;
	private BurgerMenu burgerMenu;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.webElementHelper = new WebElementHelper(driver);
		WebElementHelper.setWaitTime(60);
	}

	public void killDriver() throws Exception {
		driver.quit();
	}

	public HomePage navigateToHomePage(String url) throws Exception {
		webElementHelper.maximizeWindow().navigateTo(url);
		return initializeHomePage();
	}

	public LoginPage initializeLoginPage() throws Exception {
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public HomePage initializeHomePage() throws Exception {
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	public void initializeBurgerMenu() {
		this.burgerMenu=PageFactory.initElements(driver, BurgerMenu.class);
	}
	
	public AppointmentPage initializeAppointmentPage() throws Exception{
		return PageFactory.initElements(driver, AppointmentPage.class);
	}
	
	public AppointmentConfirmationPage initializeAppointmentConfirmationPage() throws Exception{
		return PageFactory.initElements(driver, AppointmentConfirmationPage.class);
	}
	
	BurgerMenu getBurgerMenu() throws Exception{
		return burgerMenu;
	}
}
