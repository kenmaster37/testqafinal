package com.dxhotels.runners;


import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
	features = "src/test/resources/features",
	glue = "com.dxhotels.stepdefinitions"
)
public class TestRunner {
}
