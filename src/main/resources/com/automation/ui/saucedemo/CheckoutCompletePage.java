package com.automation.ui.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage {

	@FindBy(xpath = "//span[@class='title']")
	WebElement title;

	@FindBy(css = ".complete-text")
	WebElement message;

	@FindBy(id = "back-to-products")
	WebElement btnBackToHome;

	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
		initializeBurgerMenu();
	}

	public ProductPage goToProductPage() throws Exception {
		webElementHelper.waitTillElementIsClickable(btnBackToHome).click(btnBackToHome);
		return initializeProductPage();
	}

	public CheckoutCompletePage waitForCheckoutCompletePageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(title);
		return initializeCheckoutCompletePage();
	}

	public String getShippingDetails() throws Exception{
		return message.getText();
	}

}
