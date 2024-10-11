package runner;


import org.junit.runner.RunWith;
import io.cucumber.junit.*;
@RunWith(Cucumber.class)
@CucumberOptions(
		features="/home/zadmin/website-advantagenew/src/test/java/feature/aboutfooterfeature.feature",
		glue= {"stepDef"}, 
		monochrome = true, 
		plugin = { "pretty","html:target/cucumber2.html" }
		,dryRun=false
	)

public class AboutFooterRunner{
		
	}

