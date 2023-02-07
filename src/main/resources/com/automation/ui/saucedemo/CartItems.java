package com.automation.ui.saucedemo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItems {

	public ArrayList<CartPojo> getCartItems(List<WebElement> cartItems) throws Exception {
		ArrayList<CartPojo> cartList = new ArrayList<>();
		for (int i = 0; i < cartItems.size(); i++) {
			WebElement ele = cartItems.get(i);
			String product = ele.findElement(By.cssSelector(".inventory_item_name")).getText();
			String price = ele.findElement(By.cssSelector(".inventory_item_price")).getText();
			String quantity = ele.findElement(By.cssSelector(".cart_quantity")).getText();
			CartPojo cart = new CartPojo();
			cart.setProductName(product);
			cart.setPrice(price);
			cart.setQuantity(quantity);
			cartList.add(cart);
		}
		return cartList;
	}
}
