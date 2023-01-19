package com.automation.ui.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformationPage extends BasePage {

	@FindBy(xpath = "//span[@class='title']")
	WebElement title;
	
	@FindBy(id = "cancel")
	WebElement btnCancel;
	
	@FindBy(id = "first-name")
	WebElement txtFirstName;

	@FindBy(id = "last-name")
	WebElement txtLastName;

	@FindBy(id = "postal-code")
	WebElement txtPostalCode;

	@FindBy(id = "continue")
	WebElement btnContinue;

	public CheckoutInformationPage(WebDriver driver) {
		super(driver);
		initializeBurgerMenu();
	}

	public CheckoutOverViewPage goToCheckoutOverviewPage() throws Exception {
		webElementHelper.waitTillElementIsClickable(btnContinue).click(btnContinue);
		return initializeCheckoutOverViewPage();
	}

	public CheckoutInformationPage waitForCheckoutInformationPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(title);
		return initializeCheckoutInformationPage();
	}

	public CheckoutInformationPage enterCustomerInformation(String firstName, String lastName, String postalCode)
			throws Exception {
		webElementHelper.waitTillElementIsVisible(txtFirstName).clear(txtFirstName).clear(txtLastName)
				.clear(txtPostalCode);
		txtFirstName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);
		txtPostalCode.sendKeys(postalCode);
		return initializeCheckoutInformationPage();
	}
	
	public CartPage backToCartPage() throws Exception{
		webElementHelper.waitTillElementIsVisible(btnCancel).click(btnCancel);
		return initializeCartPage();
	}

}
