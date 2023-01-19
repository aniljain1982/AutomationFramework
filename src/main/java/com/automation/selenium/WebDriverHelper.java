package com.automation.selenium;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverHelper {
	public WebDriver driver;
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	public String runParallel = "false";
	public String driverPath = File.separator + "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator;
	public static String browserVersion = "No Version found";
	public static String browserName = "No Brower Name found";

	public WebDriver getDriver() {
		if (threadDriver == null) {
			return driver;
		} else {
			return threadDriver.get();
		}

	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(ThreadLocal<RemoteWebDriver> threadDriver) {
		this.threadDriver = threadDriver;
	}

	public String getRunParallel() {
		return runParallel;
	}

	public void setRunParallel(String runParallel) {
		this.runParallel = runParallel;
	}

	public void setParallelFlag(String hubURL, String browserName) {
		if (hubURL != null && hubURL.length() > 0 && browserName.equalsIgnoreCase("safari"))
			setRunParallel("true");
	}

	public String getDriverPath() {
		return driverPath;
	}

	public void setBrowserDetails() {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		WebDriverHelper.browserName = capabilities.getBrowserName().toLowerCase();
		WebDriverHelper.browserVersion = capabilities.getVersion().toString();
	}

	public void setDriverPath(String browserName) {
		switch (browserName) {
		case "Chrome":
			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				this.driverPath = driverPath + "chromedriver";
			} else {
				this.driverPath = driverPath + "chromedriver.exe";
			}
			break;
		case "Edge":
			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				this.driverPath = driverPath + "msedgedriver";
			} else {
				this.driverPath = driverPath + "msedgedriver.exe";
			}
			break;
		}
	}

	public void initializeDriver(String browserName, String hubURL) throws Exception {
		setParallelFlag(hubURL, browserName);
		if (!runParallel.equalsIgnoreCase("true")) {
			switch (browserName) {
			case "Chrome":
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + getDriverPath());
				setDriver(new ChromeDriver());
				break;
			case "Edge":
				setDriverPath("Edge");
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + getDriverPath());
				setDriver(new EdgeDriver());
				break;
			default:
				System.out.println("Initializing Chrome Browser By Default");
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + getDriverPath());
				setDriver(new ChromeDriver());
				break;
			}

		} else {
			DesiredCapabilities capabilities;
			switch (browserName) {
			case "Chrome":
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + getDriverPath());
				capabilities = DesiredCapabilities.chrome();
				setDriver(new RemoteWebDriver(new URL(hubURL), capabilities));
				break;
			case "Edge":
				setDriverPath("Edge");
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + getDriverPath());
				capabilities = DesiredCapabilities.edge();
				setDriver(new RemoteWebDriver(new URL(hubURL), capabilities));
				break;
			default:
				System.out.println("Initializing Chrome Browser By Default");
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + getDriverPath());
				capabilities = DesiredCapabilities.chrome();
				setDriver(new RemoteWebDriver(new URL(hubURL), capabilities));
				break;
			}
		}
		setBrowserDetails();
	}

}
