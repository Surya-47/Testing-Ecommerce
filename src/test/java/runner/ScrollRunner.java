package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/home/zadmin/Desktop/temp2/website-advantagenew/src/test/java/feature/scrollFeature.feature",
        glue = "stepDef",
        plugin = {"pretty","html:target/cucumber3.html"},
        monochrome = true
)
public class ScrollRunner {
}
