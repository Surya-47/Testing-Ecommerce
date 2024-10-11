package stepDef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hookfile {
    WebDriver driver;

    // This hook runs before every scenario
    @Before
    public void setUp() throws InterruptedException {
        System.out.println("Setting up WebDriver and browser for the scenario.");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // This hook runs after every scenario
    @After
    public void tearDown() {
        System.out.println("Tearing down the WebDriver instance after scenario.");
        if (driver != null) {
            driver.quit();
        }
    }
}
