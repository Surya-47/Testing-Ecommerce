package runner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/home/zadmin/Desktop/website-advantagenew/src/test/java/feature/accountsigninfeature.feature",
        glue = "stepDef",
        plugin = {"pretty", "html:target/cucumber4.html" },monochrome = true
)
public class AccountRunner {

}
