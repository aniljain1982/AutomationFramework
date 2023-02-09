package com.automation.cucumber.runner.ui;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		// Features
		features = {"./src/main/resources/features/saucedemo/login.feature",
				"./src/main/resources/features/saucedemo/EOE.feature" },

		// Glue
		glue = { "com.automation.cucumber.ui.stepdefinition" }, plugin = { "json:target/reports/cucumber8.json",
				"rerun:target/rerun.txt" }, tags = "not @smoke", monochrome = true

)

public class SauceDemoRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider()
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
