package pl.akademiaqa;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        glue = {"pl.akademiaqa"},
        features = "src/test/resources/pl.akademiaqa",
        snippets = CAMELCASE,
        tags = "")
public class RunCucumberTest {
}
