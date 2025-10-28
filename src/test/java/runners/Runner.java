package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources",
        glue = "stepdefinitions",
        tags = "@wip",
        plugin = {"pretty",
                  "html:target/cucumber-report.html",
                  "json:target/cucumber.json"},
        monochrome = false
)
public class Runner extends AbstractTestNGCucumberTests {

}