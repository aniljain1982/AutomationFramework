package com.automation.selenium;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementHelper {
	public WebDriver driver;
	public static Integer waitTime = 60;
	public WebDriverWait webDriverWait;
	private WebElement webElement;
	private List<WebElement> webElements;

	public WebElementHelper(WebDriver driver) {
		this.driver = driver;
		webDriverWait = new WebDriverWait(driver, waitTime);
	}

	public WebElementHelper(WebElement webElement, WebDriver driver) {
		this.driver = driver;
		this.webElement = webElement;
	}

	public WebElementHelper(List<WebElement> webElements, WebDriver driver) {
		this.driver = driver;
		this.webElements = webElements;
	}

	public WebElementHelper(WebElement webElement) {
		this.webElement = webElement;
	}

	public WebElement getWebElement() {
		return webElement;
	}

	public List<WebElement> getWebElements() {
		return webElements;
	}

	public static Integer getWaitTime() {
		return waitTime;
	}

	public static void setWaitTime(Integer waitTime) {
		WebElementHelper.waitTime = waitTime;
	}

	public WebElementHelper maximizeWindow() throws Exception {
		driver.manage().window().maximize();
		return new WebElementHelper(driver);
	}

	public WebElementHelper navigateTo(String url) throws Exception {
		driver.get(url);
		return new WebElementHelper(driver);
	}
	
	public WebElement findTheElement(By byElem) throws Exception {
		return driver.findElement(byElem);
	}
	
	public WebElementHelper waitTillElementIsVisible(WebElement element) throws Exception {
		webDriverWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(element)));
		return new WebElementHelper(driver);
	}

	public WebElementHelper waitTillElementIsClickable(WebElement element) throws Exception {
		webDriverWait.until(ExpectedConditions.or(ExpectedConditions.elementToBeClickable(element)));
		return new WebElementHelper(driver);
	}

	public WebElementHelper clear(WebElement element) throws Exception {
		element.clear();
		return new WebElementHelper(driver);
	}

	public WebElementHelper click(WebElement element) throws Exception {
		element.click();
		return new WebElementHelper(driver);
	}

	public Boolean checkVisibility(WebElement element) {
		boolean visible = false;
		try {
			if (element.isDisplayed() == true) {
				visible = true;
			}
		} catch (NoSuchElementException noexcep) {
			visible = false;
		}
		return visible;
	}
	
	public Boolean checkPresence(By by) throws Exception{
		boolean present = false;
		try {
			findTheElement(by);
			present=true;
		} catch (NoSuchElementException noexcep) {
			present = false;
		}
		return present;
	}

	public Boolean checkSelected(WebElement element) {
		boolean selected = false;
		try {
			if (element.isSelected() == true) {
				selected = true;
			}
		} catch (NoSuchElementException noexcep) {
			selected = false;
		}
		return selected;
	}

	public Boolean checkEnabled(WebElement element) {
		boolean selected = false;
		try {
			if (element.isEnabled() == true) {
				selected = true;
			}
		} catch (NoSuchElementException noexcep) {
			selected = false;
		}
		return selected;
	}

	public WebElementHelper selectComboByText(WebElement element, String text) throws Exception {
		new Select(element).selectByVisibleText(text);
		return new WebElementHelper(driver);
	}

	public WebElementHelper selectComboByValue(WebElement element, String value) throws Exception {
		new Select(element).selectByValue(value);
		return new WebElementHelper(driver);
	}

	public WebElementHelper sendBoardKeys(Keys key) {
		Actions acc = new Actions(driver);
		acc.sendKeys(key).build().perform();
		return new WebElementHelper(driver);
	}

	public WebElementHelper sendTextKeys(String textString) throws Exception {
		Actions acc = new Actions(driver);
		acc.sendKeys(textString).build().perform();
		return new WebElementHelper(driver);
	}

	public WebElementHelper selectComboByIndex(WebElement element, int index) throws Exception {
		new Select(element).deselectByIndex(index);
		return new WebElementHelper(driver);
	}

	public WebElementHelper clickAndSendkeys(WebElement elem, String string) throws Exception {
		elem.click();
		elem.sendKeys(string);
		return new WebElementHelper(driver);
	}

	public WebElementHelper sendKeysTo(WebElement elem, String inputString) throws Exception {
		elem.sendKeys(inputString);
		return new WebElementHelper(driver);
	}

	public WebElementHelper sendKeysTo(By byElem, String inputString) throws Exception {
		driver.findElement(byElem).sendKeys(inputString);
		return new WebElementHelper(driver);
	}

	//// Web Element Builder
	public WebElementHelper getElementByAttributeValue(List<WebElement> webElems, String value,
			String... charactersTobeRemoved) throws Exception {
		WebElement el = webElems.stream()
				.filter(elem -> getTidyString(elem.getAttribute("value"), charactersTobeRemoved).trim().equals(value))
				.findFirst().orElse(null);
		return new WebElementHelper(el, driver);
	}

	public WebElementHelper getElementWithText(List<WebElement> webElems, String innerText,
			String... charactersTobeRemoved) throws Exception {
		WebElement el = webElems.stream().filter(
				elem -> getTidyString(elem.getAttribute("innerText"), charactersTobeRemoved).trim().equals(innerText))
				.findFirst().orElse(null);
		return new WebElementHelper(el, driver);
	}

	public WebElementHelper getNextSiblingElement() throws Exception {
		return new WebElementHelper((WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].nextElementSibling;", webElement), driver);
	}

	public WebElementHelper getParentElement() throws Exception {
		return new WebElementHelper((WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].parentElement;", webElement), driver);
	}

	public WebElementHelper getAncestorElement() throws Exception {
		return new WebElementHelper((WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].offsetParent;", webElement), driver);
	}

	public WebElementHelper getPreviousSiblingElement() throws Exception {
		return new WebElementHelper((WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].previousElementSibling;", webElement), driver);
	}

	public String getTidyString(String oldStr, String... reStr) {
		if (reStr.length > 0) {
			for (String st : reStr) {
				oldStr = oldStr.replaceAll(st, "");
			}
		}
		return oldStr.replaceAll("[^\\x00-\\x7F]", "").replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "").replace("\\p{C}", "")
				.trim().toString();
	}

}
