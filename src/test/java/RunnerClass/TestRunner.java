package RunnerClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/FeatureFile/AddItemToCart.feature",
        glue = "StepDefinition" ,
        plugin = {"pretty","json:Report/Cucumber.json","junit:Report/XMLReport.xml",
                "html:Report/cucumberReport"},
        monochrome = true)
public class TestRunner {
}
