package com.automation.ui.saucedemo;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.helper.JsonHelper;
import com.automation.helper.PropertyHelper;
import com.automation.selenium.WebDriverHelper;

public class Product {
	WebDriver driver;

	BasePage basePage;
	LoginPage loginPage;
	ProductPage productPage;

	WebDriverHelper webDriverHelper;
	PropertyHelper propertyHelper;

	@BeforeClass
	@Parameters({ "runParallel", "environment", "browser", "hubURL" })
	public void setup(String runParallel, String environment, String browser, String hubURL) throws Exception {
		webDriverHelper = new WebDriverHelper();
		webDriverHelper.initializeDriver(browser, runParallel);
		driver = webDriverHelper.getDriver();
		basePage = new BasePage(driver);
		propertyHelper = new PropertyHelper(environment, "saucedemo");
	}
	
	@Test
	public void validateCart() {
		try {
			// Login page
			loginPage = basePage.navigateToLoginPage(propertyHelper.getPropertyValue("sauceUrl"));
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
			productPage = loginPage.waitForLoginPageLoad().loginToApp(propertyHelper.getPropertyValue("sauceUserName"),
					propertyHelper.getPropertyValue("saucePassowrd")).waitForProductPageLoad();
			Assert.assertTrue(productPage.validateThatOnProductPage());
			JsonHelper jsonHelper = new JsonHelper("EndToEnd.json", "saucedemo");
			Map<String, String> product = jsonHelper.readJsonElement("EndToEndTwoProduct");
			CartPojo product1Cart = new CartPojo();
			product1Cart.setProductName(product.get("productName1"));
			product1Cart.setPrice(product.get("price1"));
			product1Cart.setQuantity(product.get("quantity1"));
			productPage.AddProductToCart(product1Cart.getProductName());
			Assert.assertEquals(productPage.getProductsInCart(), "1");
			
			CartPojo product2Cart = new CartPojo();
			product2Cart.setProductName(product.get("productName2"));
			product2Cart.setPrice(product.get("price2"));
			product2Cart.setQuantity(product.get("quantity2"));
			productPage.AddProductToCart(product2Cart.getProductName());
			Assert.assertEquals(productPage.getProductsInCart(), "2");
			
			productPage.removeAddedProductFromCart(product2Cart.getProductName());
			Assert.assertEquals(productPage.getProductsInCart(), "1");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		basePage.killDriver();
	}

}
