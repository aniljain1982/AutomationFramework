package com.automation.ui.saucedemo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.selenium.WebElementHelper;

public class CheckoutOverViewPage extends BasePage {

	@FindBy(xpath = "//span[@class='title']")
	WebElement title;

	@FindBy(id = "finish")
	WebElement btnFinish;

	@FindBy(id = "cancel")
	WebElement btnCancel;

	@FindBy(css = ".inventory_item_name")
	List<WebElement> cartItems;

	@FindBy(css = ".cart_item")
	List<WebElement> cart;

	@FindBy(css = ".summary_info")
	WebElement summaryInfo;

	@FindBy(css = ".summary_subtotal_label")
	WebElement subTotal;

	@FindBy(css = ".summary_tax_label")
	WebElement tax;

	@FindBy(css = ".summary_total_label")
	WebElement total;

	@FindBy(xpath = "//div[text()='Payment Information:']")
	WebElement payment;

	@FindBy(xpath = "//div[text()='Shipping Information:']")
	WebElement shipping;

	public CheckoutOverViewPage(WebDriver driver) {
		super(driver);
		initializeBurgerMenu();
	}

	public CheckoutCompletePage goToCheckoutCompletePage() throws Exception {
		webElementHelper.waitTillElementIsClickable(btnFinish).click(btnFinish);
		return initializeCheckoutCompletePage();
	}

	public CheckoutOverViewPage waitForCheckoutOverViewPageLoad() throws Exception {
		webElementHelper.waitTillElementIsVisible(title);
		return initializeCheckoutOverViewPage();
	}

	public ArrayList<CartPojo> getCartItems() throws Exception {
		return new CartItems().getCartItems(cart);
	}

	public String getPaymentInfo() throws Exception {
		return new WebElementHelper(payment, driver).getNextSiblingElement().getWebElement().getText();
	}

	public String getShippingInfo() throws Exception {
		return new WebElementHelper(shipping, driver).getNextSiblingElement().getWebElement().getText();
	}

	public String getSubTotal() throws Exception {
		return subTotal.getText();
	}

	public String getTotal() throws Exception {
		return total.getText();
	}

	public String getTax() throws Exception {
		return tax.getText();
	}

	public ProductPage backToProductPage() throws Exception {
		webElementHelper.waitTillElementIsVisible(btnCancel).click(btnCancel);
		return initializeProductPage();
	}
}
