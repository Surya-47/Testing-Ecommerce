package stepDef;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ScrollStepDefinition{
    private WebDriver driver;
    private HomePage demoHome;

    @Before
    public void setUp() {
        System.out.println("Setting up WebDriver and browser for the scenario.");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        demoHome = new HomePage(driver); // Initialize HomePage
    }

    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        demoHome.navigateToHomePage();
    }

    @When("I scroll down the page")
    public void i_scroll_down_the_page() throws InterruptedException {
        Thread.sleep(5000); // Wait for the page to load

        Long initialScrollPosition = demoHome.getScrollPosition();
        System.out.println("Initial scroll position: " + initialScrollPosition);

        demoHome.scrollDown(2500);
        Thread.sleep(3000); // Wait for scrolling to complete

        Long afterScrollPosition = demoHome.getScrollPosition();
        System.out.println("Scroll position after scrolling down: " + afterScrollPosition);
    }

    @When("I click on the back to top button")
    public void i_click_on_the_back_to_top_button() throws InterruptedException {
        demoHome.getScrollButton().click();
        Thread.sleep(3000); // Wait for the scroll action to complete
    }

    @Then("I should be back at the top of the page")
    public void i_should_be_back_at_the_top_of_the_page() {
        Long finalScrollPosition = demoHome.getScrollPosition();
        System.out.println("Scroll position after clicking the back to top button: " + finalScrollPosition);

        assertEquals(finalScrollPosition, Long.valueOf(0), "Failed to return to the top of the page.");
        System.out.println("Successfully returned to the top of the page.");
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down the WebDriver instance after scenario.");
        if (driver != null) {
            driver.quit();
        }
    }
}
