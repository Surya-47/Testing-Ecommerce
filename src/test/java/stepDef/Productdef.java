package stepDef;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Productdef {
    WebDriver driver;

    @Given("I am on the Home page")
    public void i_am_on_the_home_page() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.advantageonlineshopping.com/#/");
        System.out.println("I am on the Home Page");
        Thread.sleep(2000);
    }

    @When("I navigate to the headphones genre page")
    public void i_navigate_to_the_headphones_genre_page() throws InterruptedException {
        WebElement headphonesclickgenre = driver.findElement(By.id("headphonesImg"));
        headphonesclickgenre.click();        
        Thread.sleep(2000);

        // Optionally click on a specific headphone item
        WebElement selectheadphone = driver.findElement(By.xpath("//img[@id='13']"));
        selectheadphone.click(); // Uncomment this line if you want to select the headphone
        Thread.sleep(2000);
    }

    @Then("only available headphones should be displayed")
    public void only_available_headphones_should_be_displayed() {
        // Locate the element that contains the availability text
        WebElement availabilityText = driver.findElement(By.cssSelector("h2[class='roboto-thin screen768 ng-binding'] span[class='roboto-medium ng-scope']"));

        // Get the actual text
        String actualText = availabilityText.getText().trim(); 
        
        try {
            // Assert that the product is not marked as "SOLD OUT" and log a defect if it is
            if (actualText.equalsIgnoreCase("SOLD OUT")) {
                // Fail the test if sold out
                Assert.fail("Defect: The product is marked as SOLD OUT! and should not be displayed"); 
            } else if (actualText.isEmpty()) {
                System.out.println("The product is available for purchase.");
            } 
        }
        //to close finally
        finally {
            driver.quit(); 
        }
    }

}
