package com.automation.ui.saucedemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.selenium.WebElementHelper;

public class ProductPage extends BasePage {

	@FindBy(xpath = "//span[@class='title']")
	WebElement title;

	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> inventoryItems;

	@FindBy(id = "shopping_cart_container")
	WebElement cartContainer;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		initializeBurgerMenu();
		WebElementHelper.setWaitTime(60);
	}

	public ProductPage waitForProductPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(title);
		return initializeProductPage();
	}
	
	public boolean validateThatOnProductPage() {
		return webElementHelper.checkVisibility(title);
	}

	public void AddProductToCart(String product) throws Exception {
		webElementHelper.getElementWithText(inventoryItems, product).getParentElement().getParentElement()
				.getNextSiblingElement().getWebElement().findElement(By.xpath("//button[text()='Add to cart']"))
				.click();

	}

	public CartPage goToCartPage() throws Exception {
		webElementHelper.waitTillElementIsClickable(cartContainer).click(cartContainer);
		return initializeCartPage();
	}

}
