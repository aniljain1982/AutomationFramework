package com.automation.ui.saucedemo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
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

public class EndToEnd {
	WebDriver driver;
	BasePage basePage;
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutInformationPage checkoutInformationPage;
	CheckoutOverViewPage checkoutOverViewPage;
	CheckoutCompletePage checkoutCompletePage;
	WebDriverHelper webDriverUtils;
	PropertyHelper propertyHelper;

	@BeforeClass
	@Parameters({ "runParallel", "environment", "browser", "hubURL" })
	public void setup(String runParallel, String environment, String browser, String hubURL) throws Exception {
		webDriverUtils = new WebDriverHelper();
		webDriverUtils.initializeDriver(browser, runParallel);
		driver = webDriverUtils.getDriver();
		basePage = new BasePage(driver);
		propertyHelper = new PropertyHelper(environment, "saucedemo");
	}

	/**
	 *  1.	Launch https://www.saucedemo.com/
		2.	Login to app
		3.	Add a product
		4.	Go to cart page and verify cart items
		5.	Go to checkout information page and add customer details
		6.	Go to checkout overview page and verify the cart items
		7.	Go to checkout complete page and verify dispatch message
		8.	Go back to product page
		9.	Logout
	 */
	@Test
	public void endToEndOneProduct() {
		try {
			//Login page
			loginPage = basePage.navigateToLoginPage(propertyHelper.getPropertyValue("sauceUrl"));
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
			
			//Product Page
			productPage = loginPage.waitForLoginPageLoad().loginToApp(propertyHelper.getPropertyValue("sauceUserName"),
					propertyHelper.getPropertyValue("saucePassowrd")).waitForProductPageLoad();
			JsonHelper jsonHelper = new JsonHelper("EndToEnd.json", "saucedemo");
			Map<String, String> product_1 = jsonHelper.readJsonElement("EndToEndOneProduct");
			CartPojo product1Cart = new CartPojo();
			product1Cart.setProductName(product_1.get("productName"));
			product1Cart.setPrice(product_1.get("price"));
			product1Cart.setQuantity(product_1.get("quantity"));
			productPage.AddProductToCart(product1Cart.getProductName());
			Assert.assertEquals(productPage.title.getText(), "PRODUCTS");
			
			//Cart page
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			cartPage = productPage.goToCartPage().waitForCartPageLoad();
			Assert.assertEquals(cartPage.title.getText(), "YOUR CART");
			expectedData.add(product1Cart);
			assertThat(cartPage.getCart(), is(expectedData));
			
			//Checkout Information page
			checkoutInformationPage = cartPage.goToCheckoutInformationPage().waitForCheckoutInformationPageLoad();
			checkoutInformationPage.enterCustomerInformation(product_1.get("firstName"), product_1.get("lastName"),
					product_1.get("postalCode"));
			
			//Checkout overview page
			checkoutOverViewPage = checkoutInformationPage.goToCheckoutOverviewPage().waitForCheckoutOverViewPageLoad();
			actualData = new ArrayList<>();
			expectedData = new ArrayList<>();
			expectedData.add(product1Cart);
			for (CartPojo cartPojo : cartPage.getCart()) {
				actualData.add(cartPojo);
			}
			expectedData.add(product_1.get("card"));
			actualData.add(checkoutOverViewPage.getPaymentInfo());
			expectedData.add(product_1.get("shipping"));
			actualData.add(checkoutOverViewPage.getShippingInfo());
			expectedData.add(product_1.get("itemTotal"));
			actualData.add(checkoutOverViewPage.getSubTotal());
			expectedData.add(product_1.get("tax"));
			actualData.add(checkoutOverViewPage.getTax());
			expectedData.add(product_1.get("total"));
			actualData.add(checkoutOverViewPage.getTotal());
			assertThat(actualData, is(expectedData));
			
			//Checkout complete page
			checkoutCompletePage = checkoutOverViewPage.goToCheckoutCompletePage().waitForCheckoutCompletePageLoad();
			Assert.assertEquals(checkoutCompletePage.getShippingDetails(),
					"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
			
			//Back to product page
			productPage = checkoutCompletePage.goToProductPage().waitForProductPageLoad();
			Assert.assertEquals(productPage.title.getText(), "PRODUCTS");
			
			//Logout
			loginPage=productPage.getBurgerMenu().logout().waitForLoginPageLoad();
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 *  1.	Launch https://www.saucedemo.com/
		2.	Login to app
		3.	Add two products
		4.	Go to cart page and verify cart items
		5.	Go to checkout information page and add customer details
		6.	Go to checkout overview page and verify the cart items
		7.	Go to checkout complete page and verify dispatch message
		8.	Go back to product page
		9.	Logout
	 */
	@Test
	public void endToEndTwoProduct() {
		try {
			//Login page
			loginPage = basePage.navigateToLoginPage(propertyHelper.getPropertyValue("sauceUrl"));
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
			
			//Product Page
			productPage = loginPage.waitForLoginPageLoad().loginToApp(propertyHelper.getPropertyValue("sauceUserName"),
					propertyHelper.getPropertyValue("saucePassowrd")).waitForProductPageLoad();
			JsonHelper jsonHelper = new JsonHelper("EndToEnd.json", "saucedemo");
			Map<String, String> product = jsonHelper.readJsonElement("EndToEndTwoProduct");
			CartPojo product1Cart = new CartPojo();
			product1Cart.setProductName(product.get("productName1"));
			product1Cart.setPrice(product.get("price1"));
			product1Cart.setQuantity(product.get("quantity1"));
			productPage.AddProductToCart(product1Cart.getProductName());
			CartPojo product2Cart = new CartPojo();
			product2Cart.setProductName(product.get("productName2"));
			product2Cart.setPrice(product.get("price2"));
			product2Cart.setQuantity(product.get("quantity2"));
			productPage.AddProductToCart(product2Cart.getProductName());
			Assert.assertEquals(productPage.title.getText(), "PRODUCTS");
			
			//Cart page
			ArrayList<Object> expectedData = new ArrayList<>();
			cartPage = productPage.goToCartPage().waitForCartPageLoad();
			Assert.assertEquals(cartPage.title.getText(), "YOUR CART");
			expectedData.add(product1Cart);
			expectedData.add(product2Cart);
			assertThat(cartPage.getCart(), is(expectedData));
			
			//Checkout Information page
			checkoutInformationPage = cartPage.goToCheckoutInformationPage().waitForCheckoutInformationPageLoad();
			checkoutInformationPage.enterCustomerInformation(product.get("firstName"), product.get("lastName"),
					product.get("postalCode"));
			
			//Checkout overview page
			checkoutOverViewPage = checkoutInformationPage.goToCheckoutOverviewPage().waitForCheckoutOverViewPageLoad();
			ArrayList<Object> actualData = new ArrayList<>();
			expectedData = new ArrayList<>();
			expectedData.add(product1Cart);
			expectedData.add(product2Cart);
			for (CartPojo cartPojo : cartPage.getCart()) {
				actualData.add(cartPojo);
			}
			expectedData.add(product.get("card"));
			actualData.add(checkoutOverViewPage.getPaymentInfo());
			expectedData.add(product.get("shipping"));
			actualData.add(checkoutOverViewPage.getShippingInfo());
			expectedData.add(product.get("itemTotal"));
			actualData.add(checkoutOverViewPage.getSubTotal());
			expectedData.add(product.get("tax"));
			actualData.add(checkoutOverViewPage.getTax());
			expectedData.add(product.get("total"));
			actualData.add(checkoutOverViewPage.getTotal());
			assertThat(actualData, is(expectedData));
			
			//Checkout complete page
			checkoutCompletePage = checkoutOverViewPage.goToCheckoutCompletePage().waitForCheckoutCompletePageLoad();
			Assert.assertEquals(checkoutCompletePage.getShippingDetails(),
					"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
			
			//Back to product page
			productPage = checkoutCompletePage.goToProductPage().waitForProductPageLoad();
			Assert.assertEquals(productPage.title.getText(), "PRODUCTS");
			
			//Logout
			loginPage=productPage.getBurgerMenu().logout().waitForLoginPageLoad();
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 *  1.	Launch https://www.saucedemo.com/
		2.	Login to app
		3.	Add two product
		4.	Go to cart page and verify cart items
		5.  Remove a product and verify cart items
		6.  Go to back to product page
		7.  Add a product
		8.	Go to cart page and verify cart items
		9.	Go to checkout information page and add customer details
		10.	Go to checkout overview page and verify the cart items
		11.	Go to checkout complete page and verify dispatch message
		12.	Go back to product page
		13.	Logout
	 */
	@Test
	public void endToEndRemoveProduct() {
		try {
			//Login page
			loginPage = basePage.navigateToLoginPage(propertyHelper.getPropertyValue("sauceUrl"));
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
			
			//Product Page
			productPage = loginPage.waitForLoginPageLoad().loginToApp(propertyHelper.getPropertyValue("sauceUserName"),
					propertyHelper.getPropertyValue("saucePassowrd")).waitForProductPageLoad();
			JsonHelper jsonHelper = new JsonHelper("EndToEnd.json", "saucedemo");
			Map<String, String> product = jsonHelper.readJsonElement("EndToEndTwoProduct");
			CartPojo product1Cart = new CartPojo();
			product1Cart.setProductName(product.get("productName1"));
			product1Cart.setPrice(product.get("price1"));
			product1Cart.setQuantity(product.get("quantity1"));
			productPage.AddProductToCart(product1Cart.getProductName());
			CartPojo product2Cart = new CartPojo();
			product2Cart.setProductName(product.get("productName2"));
			product2Cart.setPrice(product.get("price2"));
			product2Cart.setQuantity(product.get("quantity2"));
			productPage.AddProductToCart(product2Cart.getProductName());
			Assert.assertEquals(productPage.title.getText(), "PRODUCTS");
			
			//Cart page
			ArrayList<Object> expectedData = new ArrayList<>();
			cartPage = productPage.goToCartPage().waitForCartPageLoad();
			Assert.assertEquals(cartPage.title.getText(), "YOUR CART");
			expectedData.add(product1Cart);
			expectedData.add(product2Cart);
			assertThat(cartPage.getCart(), is(expectedData));
			
			//remove a product
			cartPage.removeItemFromCart(product1Cart.getProductName());
			expectedData = new ArrayList<>();
			expectedData.add(product2Cart);
			assertThat(cartPage.getCart(), is(expectedData));
			
			//continue shopping
			productPage = cartPage.gotBackToProductPage().waitForProductPageLoad();
			productPage.AddProductToCart(product1Cart.getProductName());
			cartPage = productPage.goToCartPage().waitForCartPageLoad();
			expectedData.add(product1Cart);
			assertThat(cartPage.getCart(), is(expectedData));
			
			//Checkout Information page
			checkoutInformationPage = cartPage.goToCheckoutInformationPage().waitForCheckoutInformationPageLoad();
			checkoutInformationPage.enterCustomerInformation(product.get("firstName"), product.get("lastName"),
					product.get("postalCode"));
			
			//Checkout overview page
			checkoutOverViewPage = checkoutInformationPage.goToCheckoutOverviewPage().waitForCheckoutOverViewPageLoad();
			ArrayList<Object> actualData = new ArrayList<>();
			expectedData = new ArrayList<>();
			expectedData.add(product2Cart);
			expectedData.add(product1Cart);
			for (CartPojo cartPojo : cartPage.getCart()) {
				actualData.add(cartPojo);
			}
			expectedData.add(product.get("card"));
			actualData.add(checkoutOverViewPage.getPaymentInfo());
			expectedData.add(product.get("shipping"));
			actualData.add(checkoutOverViewPage.getShippingInfo());
			expectedData.add(product.get("itemTotal"));
			actualData.add(checkoutOverViewPage.getSubTotal());
			expectedData.add(product.get("tax"));
			actualData.add(checkoutOverViewPage.getTax());
			expectedData.add(product.get("total"));
			actualData.add(checkoutOverViewPage.getTotal());
			assertThat(actualData, is(expectedData));
			
			//Checkout complete page
			checkoutCompletePage = checkoutOverViewPage.goToCheckoutCompletePage().waitForCheckoutCompletePageLoad();
			Assert.assertEquals(checkoutCompletePage.getShippingDetails(),
					"Your order has been dispatched, and will arrive just as fast as the pony can get there!");
			
			//Back to product page
			productPage = checkoutCompletePage.goToProductPage().waitForProductPageLoad();
			Assert.assertEquals(productPage.title.getText(), "PRODUCTS");
			
			//Logout
			loginPage=productPage.getBurgerMenu().logout().waitForLoginPageLoad();
			Assert.assertTrue(loginPage.validateThatOnLoginPage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		basePage.killDriver();
	}

}
