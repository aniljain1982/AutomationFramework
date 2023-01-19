package com.automation.ui.saucedemo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.selenium.WebElementHelper;

public class CartPage extends BasePage {

	@FindBy(xpath = "//span[@class='title']")
	WebElement title;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> cartItems;

	@FindBy(css = ".cart_item")
	List<WebElement> cart;

	@FindBy(id = "checkout")
	WebElement btnCheckout;

	@FindBy(id = "continue-shopping")
	WebElement btnContinueShopping;

	public CartPage(WebDriver driver) {
		super(driver);
		WebElementHelper.setWaitTime(60);
	}

	public CartPage waitForCartPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(title);
		initializeBurgerMenu();
		return initializeCartPage();
	}

	public ArrayList<CartPojo> getCartItems() throws Exception {
		return new CartItems().getCartItems(cart);
	}

	public CheckoutInformationPage goToCheckoutInformationPage() throws Exception {
		webElementHelper.waitTillElementIsClickable(btnCheckout).click(btnCheckout);
		return initializeCheckoutInformationPage();
	}

	public ProductPage gotBackToProductPage() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnContinueShopping).click(btnContinueShopping);
		return initializeProductPage();
	}

	public void removeItemFromCart(String product) throws Exception {
		WebElement ele = webElementHelper.getElementWithText(cartItems, product).getParentElement().getParentElement()
				.getWebElement();
		ele.findElement(By.xpath("//button[text()='Remove']")).click();
	}

}
