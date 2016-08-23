package org.planotius.steps;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 *
 * @author ggodoy
 */
@RunWith(Cucumber.class)

@CucumberOptions(
features = "src/test/resources/features",
plugin = {"pretty", "html:target/cucumber-html-report"}
)
public class TestRunner { }
