package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonfiles.AdvantageBaseClass;
import commonfiles.AdvantageExtent; 
import pages.Ourproducthome;
import pages.OurproductshomePF;

import com.aventstack.extentreports.Status; 

public class OurProductsTestCases extends AdvantageBaseClass {
    private Ourproducthome oph;
    private Properties prop;
    private String url;
    private static final String PROPPATH = System.getProperty("user.dir");
    private WebDriverWait wait;
    private OurproductshomePF opf;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional("chrome") String browser) throws IOException {
        // Load properties file
        FileInputStream fis = new FileInputStream(PROPPATH + "/AdvantageProperties.properties");
        prop = new Properties();
        prop.load(fis);
        url = prop.getProperty("url");
        // Initialize browser from AdvantageBaseClass
        invokeBrowser(browser);
        
        // Initialize Extent Report instance
        AdvantageExtent.getInstance(); 
    }

    /// Test-1: Add to cart functionality
    @Test(priority = 0)
    public void AdvantageAddToCart() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        
        // Wait for page to load
        wait.until(ExpectedConditions.urlToBe(url));

        // Creating Home page object
        opf = new OurproductshomePF(driver);
        
        // Using object to interact with Home page and genres in it
        opf.clickSpeakerGenre().click();
        Thread.sleep(2000);
        opf.clickSpeakerItem().click();
        Thread.sleep(2000);
        opf.clickAddSpeakerToCart().click();
        Thread.sleep(2000);
        
        String actualTitle = driver.getTitle().replace("\u00A0", "").trim();
        System.out.println("Actual Title: " + actualTitle);
 
        // Assert that the page title is "Advantage Shopping"
        Assert.assertEquals("Advantage Shopping", actualTitle);
        AdvantageExtent.createTest("Advantage Add to Cart").log(Status.PASS, "Add to cart functionality worked successfully.");
        Thread.sleep(1000);
        
        /// Calling screenshot method from BaseClass
        screenShot();
    }

    /// Test-2: To check for Filter Functionality
    @Test(priority = 1)
    public void AdvantageFilteredProduct() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        
        // Wait for page load
        wait.until(ExpectedConditions.urlToBe(url));

        oph = new Ourproducthome(driver);

        // Using object to interact with elements
        oph.clickLaptopGenreSelect().click();
        Thread.sleep(2000);
        oph.clickDropDown().click();
        Thread.sleep(2000);
        oph.clickCheckBox().click();
        Thread.sleep(2000);
        oph.clickChromeOsLaptop().click();
        Thread.sleep(2000);
        
        String currentUrl = driver.getCurrentUrl();
        System.out.println("The current url is :" + currentUrl);
        
        // Asserting for URL
        Assert.assertEquals("https://www.advantageonlineshopping.com/#/product/9", currentUrl);
        AdvantageExtent.createTest("Advantage Filtered Product").log(Status.PASS, "Filter functionality worked successfully."); 
        screenShot();
        Thread.sleep(3000);
    }

    /// Test-3: Hover over product and print review
    @Test(priority = 2)
    public void AdvantageHoverAndPrintReview() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        
        wait.until(ExpectedConditions.urlToBe(url)); // Wait for page load
        oph = new Ourproducthome(driver); // Create an instance with the driver object
        
        // Select headphones
        oph.selectHeadphonesGenre().click();
        Thread.sleep(2000);

        WebElement headphonesProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"15\"]")));
        headphonesProduct.click();
        Thread.sleep(2000);

        WebElement headphoneImageHover = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@id='mainImg']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(headphoneImageHover).perform();
        Thread.sleep(2000);

        // Wait for the review section and print the review text on console
        WebElement reviewSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[3]/section[1]/article[1]/div[2]/div[1]/div[1]/div[2]/div[1]/p[3]")));
        String reviewText = reviewSection.getText();
        
        screenShot();
        System.out.println("Product Review: " + reviewText);
        AdvantageExtent.createTest("Advantage Hover and Print Review").log(Status.PASS, "Hover and print review functionality worked successfully."); // Log result
    }

    /// Test-4: Play video trailer and verify playback
    @Test(priority = 3)
    public void AdvantagePlayVideoAndVerifyPlayback() throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe(url)); // Wait for page load

        // Navigate to the headphones section
        oph = new Ourproducthome(driver);
        oph.selectHeadphoneImage().click();
        Thread.sleep(2000);

        // Clicking on a particular headphone product
        oph.headphoneProduct().click();
        Thread.sleep(2000);

        // Ensure the video is clickable and then click to play
        WebElement playButtonVideo = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("video")));
        playButtonVideo.click();
        Thread.sleep(2000);
        screenShot();

        WebElement videoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ng-binding']//video")));
        Actions actions = new Actions(driver);

        // Pause for 3 seconds to ensure smooth playback and load
        Thread.sleep(3000);

        // Get the current playback time of the video
        double currentTime = (Double) ((JavascriptExecutor) driver).executeScript("return arguments[0].currentTime;", videoElement);
        Assert.assertTrue(currentTime > 0, "Video did not play.");
        // This is to avoid instant closing of the browser after recording time
        Thread.sleep(3000); 
        System.out.println("Video played successfully. Final Current Time: " + currentTime);
        AdvantageExtent.createTest("Advantage Play Video and Verify Playback").log(Status.PASS, "Video played successfully.");
    }

    @AfterClass
    public void tearDown() {
    	// Flushing the Extent Reports to save 
        AdvantageExtent.getInstance().flush();
        closeBrowser(); 
    }
}
