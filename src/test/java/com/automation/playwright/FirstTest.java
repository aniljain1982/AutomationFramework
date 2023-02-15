package com.automation.playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstTest {
	
	@Test
	public void test() {
		Playwright pw=Playwright.create();
		BrowserType browserType=pw.chromium();
		Browser browser=browserType.launch();
		Page page=browser.newPage();
		page.navigate("https://katalon-demo-cura.herokuapp.com/");
		page.locator("#menu-toggle").click();
		page.locator("//a[text()='Login']").click();
		page.locator("#txt-username").type("John Doe");
		page.locator("#txt-password").type("ThisIsNotAPassword");
		page.locator("#btn-login").click();
		System.out.println(page.title());
	}
	

}
