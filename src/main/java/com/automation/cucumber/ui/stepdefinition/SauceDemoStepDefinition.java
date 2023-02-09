package com.automation.cucumber.ui.stepdefinition;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;

import org.testng.Assert;

import com.automation.ui.saucedemo.CartPage;
import com.automation.ui.saucedemo.CartPojo;
import com.automation.ui.saucedemo.CheckoutCompletePage;
import com.automation.ui.saucedemo.CheckoutInformationPage;
import com.automation.ui.saucedemo.CheckoutOverViewPage;
import com.automation.ui.saucedemo.LoginPage;
import com.automation.ui.saucedemo.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SauceDemoStepDefinition extends BaseStepDefinition {

	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutInformationPage checkoutInformationPage;
	CheckoutOverViewPage checkoutOverViewPage;
	CheckoutCompletePage checkoutCompletePage;

	@After
	public void tearDown() throws Exception {
		basePage.killDriver();
	}

	@Given("^User is on login page$")
	public void user_is_on_login_page() throws Exception {
		loginPage = basePage.navigateToLoginPage("https://www.saucedemo.com/").waitForLoginPageLoad();
	}

	@When("The user logins to app with username as {string} and password as {string}")
	public void the_user_logins_to_app_with_username_as_and_password_as(String username, String password)
			throws Exception {
		loginPage.loginToApp(username, password);
	}

	@Then("The user should login successfully and is brought to the product page")
	public void the_user_should_login_successfully_and_is_brought_to_the_inventory_page() throws Exception {
		productPage = loginPage.initializeProductPage().waitForProductPageLoad();
		Assert.assertTrue(productPage.validateThatOnProductPage());
	}

	@Then("The user should to get a error message {string}")
	public void the_user_should_to_get_a_error_message(String message) throws Exception {
		Assert.assertEquals(loginPage.getErrorMessage(), message);
	}

	@Given("The user adds product {string} to cart")
	public void the_user_adds_product_to_cart(String product) throws Exception {
		productPage.AddProductToCart(product);
	}

	@Then("user navigate to cart page")
	public void user_navigate_to_cart_page() throws Exception {
		cartPage = productPage.goToCartPage().waitForCartPageLoad();
	}

	@Then("user verify that product name as {string}, price as {string} and quantity as {string}")
	public void user_verify_that_product_name_as_price_as_and_quantity_as(String product, String price, String quantity)
			throws Exception {
		ArrayList<CartPojo> expected = new ArrayList<>();
		CartPojo expectedCartPojo = new CartPojo();
		expectedCartPojo.setProductName(product);
		expectedCartPojo.setPrice(price);
		expectedCartPojo.setQuantity(quantity);
		expected.add(expectedCartPojo);
		ArrayList<CartPojo> actual = cartPage.getCartItems();
		Assert.assertEquals(actual, expected);
	}

	@Then("user navigate to checkout information page")
	public void user_navigate_to_checkout_information_page() throws Exception {
		checkoutInformationPage = cartPage.goToCheckoutInformationPage().waitForCheckoutInformationPageLoad();
	}

	@Then("user enter the first name as {string}, last name as {string} and postal code as {string}")
	public void user_enter_the_first_name_as_last_name_as_and_postal_code_as(String firstName, String lastName,
			String postalCode) throws Exception {
		checkoutInformationPage.enterCustomerInformation(firstName, lastName, postalCode);
	}

	@Then("user navigate to checkout overview page")
	public void user_navigate_to_checkout_overview_page() throws Exception {
		checkoutOverViewPage = checkoutInformationPage.goToCheckoutOverviewPage().waitForCheckoutOverViewPageLoad();
	}

	@Then("user verify that product name is {string}, price is {string}, quantity is {string}, card is {string} , shipping is {string}, item total is {string}, tax is {string} and total is {string}")
	public void verifyCheckoutCart(String product, String price, String quantity, String card, String shipping,
			String itemTotal, String tax, String total) throws Exception {
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		CartPojo product1Cart = new CartPojo();
		product1Cart.setProductName(product);
		product1Cart.setPrice(price);
		product1Cart.setQuantity(quantity);
		expectedData.add(product1Cart);
		expectedData.add(card);
		expectedData.add(shipping);
		expectedData.add(itemTotal);
		expectedData.add(tax);
		expectedData.add(total);

		for (CartPojo cartPojo : cartPage.getCartItems()) {
			actualData.add(cartPojo);
		}
		actualData.add(checkoutOverViewPage.getPaymentInfo());
		actualData.add(checkoutOverViewPage.getShippingInfo());
		actualData.add(checkoutOverViewPage.getSubTotal());
		actualData.add(checkoutOverViewPage.getTax());
		actualData.add(checkoutOverViewPage.getTotal());
		assertThat(actualData, is(expectedData));
	}

	@Then("user navigates to checkout complete page")
	public void user_navigates_to_checkout_complete_page() throws Exception {
		checkoutCompletePage = checkoutOverViewPage.goToCheckoutCompletePage().waitForCheckoutCompletePageLoad();
	}

}
