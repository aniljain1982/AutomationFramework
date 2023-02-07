package com.automation.cucumber.runner.api;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/main/resources/features/herokuapp.feature",

 glue = "com.automation.cucumber.api.stepdefinition" )

public class HerokuappTestRunner {

}