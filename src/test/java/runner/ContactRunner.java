package runner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/feature/contactfeature.feature",
        glue = "stepDef",
        plugin = {"pretty","html:target/cucumber1.html"},
        monochrome = true
)
public class ContactRunner {

}
