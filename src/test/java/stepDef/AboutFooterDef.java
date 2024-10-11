package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AboutFooterDef {

    WebDriver driver;
    WebDriverWait wait;

    @Given("the Advantage browser is open")
    public void advantage_browser_is_open() {
        // Set up Chrome WebDriver
        //System.setProperty("webdriver.chrome.driver", "path_to_chromedriver"); // Replace with your actual path to ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
    }

    @When("the user is on the product page")
    public void the_user_is_on_the_product_page() {
        // Navigate to the Advantage Online Shopping product page
        driver.get("https://www.advantageonlineshopping.com");
        
        // Example of navigating to a product page (you may need to adjust it to match your actual product selection process)
        WebElement productCategory = wait.until(ExpectedConditions.elementToBeClickable(By.id("headphonesTxt"))); // e.g., Headphones category
        productCategory.click();

        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='productName ng-binding']"))); // Example product
        productLink.click();
    }

    @Then("the product details should be displayed correctly")
    public void the_product_details_should_be_displayed_correctly() {
        // Fetch product name
    	
    	//*[@id="productProperties"]/div[2]/e-sec-plus-minus/div/div[3]
        WebElement productIncrement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#productProperties > div.smoolMargin > e-sec-plus-minus > div > div.plus")));
        productIncrement.click();
        productIncrement.click();
        productIncrement.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed

        WebElement productAdd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#productProperties > div.smoolMargin > e-sec-plus-minus > div > div.plus")));
        productAdd.click();
        
        WebElement productDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Description\"]/p")));
        String ProductText=productDescription.getText();
        System.out.println(ProductText);
        
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed

        // Close the browser after the test is done
        driver.quit();
    }
}
