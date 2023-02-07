package com.automation.ui.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.selenium.WebElementHelper;

public class BasePage {
	public WebDriver driver;
	public WebElementHelper webElementHelper;
	BurgerMenu burgerMenu;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.webElementHelper = new WebElementHelper(driver);
		WebElementHelper.setWaitTime(60);
	}

	public void killDriver() throws Exception {
		driver.quit();
	}

	public LoginPage navigateToLoginPage(String url) throws Exception {
		webElementHelper.maximizeWindow().navigateTo(url);
		return initializeLoginPage();
	}

	public LoginPage initializeLoginPage() {
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public ProductPage initializeProductPage() {
		return PageFactory.initElements(driver, ProductPage.class);
	}

	public CartPage initializeCartPage() {
		return PageFactory.initElements(driver, CartPage.class);
	}

	public CheckoutInformationPage initializeCheckoutInformationPage() {
		return PageFactory.initElements(driver, CheckoutInformationPage.class);
	}

	public CheckoutOverViewPage initializeCheckoutOverViewPage() {
		return PageFactory.initElements(driver, CheckoutOverViewPage.class);
	}

	public CheckoutCompletePage initializeCheckoutCompletePage() {
		return PageFactory.initElements(driver, CheckoutCompletePage.class);
	}
	
	public void initializeBurgerMenu() {
		this.burgerMenu=PageFactory.initElements(driver, BurgerMenu.class);
		//return PageFactory.initElements(driver, BurgerMenu.class);
	}
	
	BurgerMenu getBurgerMenu() throws Exception{
		return burgerMenu;
	}
}
