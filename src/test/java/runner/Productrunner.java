package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature/ourproductfeature.feature",
        glue = "stepDef",
        plugin = {"pretty","html:target/cucumber.html"},
        monochrome = true
)
public class Productrunner {
}
