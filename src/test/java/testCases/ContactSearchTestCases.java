package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonfiles.AdvantageBaseClass;
import commonfiles.AdvantageExtent;
import pages.ContactSearch;
import pages.ContactSearchPF;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
public class ContactSearchTestCases extends AdvantageBaseClass{
	ContactSearch CH;
	ContactSearchPF CPF;
	String url; // Class variable to hold the URL
	private static final String PROPPATH = System.getProperty("user.dir");
	
	 @Parameters({"browser"})
	    @BeforeClass
	    public void setup(String browser) throws IOException {
	        // Load properties file and get the URL only once
		 FileInputStream fis = new FileInputStream(PROPPATH + "/AdvantageProperties.properties");
	        Properties prop = new Properties();
	        prop.load(fis);
	        
	        url = prop.getProperty("url"); // Store URL in the class variable
	        invokeBrowser(browser); // Open the browser once
	        // Initialize Extent Report instance
	        AdvantageExtent.getInstance(); 
	    }
	 
	//TEST 1 : Test case for Icons Check
	  
	    @Test (priority=0) //(enabled=false) 
	    public void IconsCheck() throws InterruptedException, IOException {
	        driver.get(url); // Use the stored URL
	        CH = new ContactSearch(driver);
	     // Wait for the loader to disappear if present
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
	        
	     
	        String actualTitle = driver.getTitle().replace("\u00A0", "").trim();
	        System.out.println("Actual Title: " + actualTitle);

	        // Assert that the page title is "Advantage Shopping"
	        Assert.assertEquals("Advantage Shopping", actualTitle);


	        // Find the Contact Us element
	        WebElement contactUsButton = wait.until(ExpectedConditions.visibilityOf(CH.ContactUs()));
	        screenShot();
	        // Ensure the element is visible in the viewport
	        scrollToElement(contactUsButton);
	        Thread.sleep(500); // Brief wait to ensure the scroll is completed

	        // Click the Contact Us button with retries
	        clickElementWithRetries(wait, contactUsButton, "Contact Us");

	        // Find the ChatBot element
	        WebElement chatBotButton = wait.until(ExpectedConditions.visibilityOf(CH.ChatBot())); 

	        // Ensure the ChatBot element is visible
	        scrollToElement(chatBotButton);
	        Thread.sleep(500); // Brief wait to ensure the scroll is completed

	        // Click the ChatBot button with retries
	        clickElementWithRetries(wait, chatBotButton, "ChatBot");
	        screenShot();
	        
	     // Switch to the new window (ChatBot)
	        String originalWindow = driver.getWindowHandle();
	        for (String windowHandle : driver.getWindowHandles()) {
	            if (!windowHandle.equals(originalWindow)) {
	                driver.switchTo().window(windowHandle);
	                break;
	            }
	        }

	        // Assert that the current URL is the expected ChatBot URL
	        String currentUrl = driver.getCurrentUrl();
	        Assert.assertEquals("https://www.advantageonlineshopping.com/chat.html", currentUrl);

	        String chatbotTitle = driver.getTitle().trim();
	        System.out.println("ChatBot Page Title: " + chatbotTitle);
	        Assert.assertEquals("Advantage Online Shopping Demo Support Chat", chatbotTitle); 
	        
	     // Switch back to the original window (main webpage)
	        driver.switchTo().window(originalWindow);
	      
	        String returnTitle = driver.getTitle().replace("\u00A0", "").trim();
	        System.out.println("Returned to Main Page Title: " + returnTitle);
	        Assert.assertEquals("Advantage Shopping", returnTitle);
	        AdvantageExtent.createTest("Advantage Icons Check").log(Status.PASS, "All the Icons clicked successfully.");

	    }
	    
	 // TEST 2 : Test to check the Contact us without selecting category and product   
	    
	    @Test  (priority=1) //(enabled=false) 
	    public void ContactUsCheck() throws InterruptedException, IOException {
	    	String email = getProperty("email");
	    	String subject = getProperty("subject");
	        driver.get(url); // Use the stored URL
	        CH = new ContactSearch(driver);
	        // Wait for the loader to disappear if present
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

	        // Find the Contact Us element
	        WebElement contactUsButton = wait.until(ExpectedConditions.visibilityOf(CH.ContactUs()));

	        // Ensure the element is visible in the viewport
	        scrollToElement(contactUsButton);
	        Thread.sleep(500); // Brief wait to ensure the scroll is completed

	        // Click the Contact Us button with retries
	        clickElementWithRetries(wait, contactUsButton, "Contact Us");
	        Thread.sleep(2000);
	        CH.Email().click();
	        Thread.sleep(2000);
	        CH.Email().sendKeys(email);
	        screenShot();
	        Thread.sleep(2000);
	        CH.Subject().click();
	        Thread.sleep(2000);
	        CH.Subject().sendKeys(subject);
	        screenShot();
	        Thread.sleep(2000);
	        CH.Send().click();
	        Thread.sleep(2000);
	        screenShot();
	        CH.Continue().click();
	        Thread.sleep(2000);
	        AdvantageExtent.createTest("Advantage Contact us Check").log(Status.PASS, "Contact us submitted without selecting the category and product.");
	        screenShot();
	    }
	    
	  //TEST 3 : Test case to check the SearchButton is working without specifying any input
	    
	    @Test  (priority=2) //(enabled=false) 
	    public void SearchDefect() throws InterruptedException, IOException {
	    	
	        //String SearchInput = getProperty("SearchInput");
	        driver.get(url); // Use the stored URL
	        CH = new ContactSearch(driver);
	        CH.Search().click();
	        //Thread.sleep(2000);
	        CH.SearchText().sendKeys("",Keys.ENTER);
	        Thread.sleep(2000);
	        screenShot();
	        Thread.sleep(2000);
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement searchResultLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchResultLabel")));

	        // Assert that the search result label is displayed
	        Assert.assertTrue(searchResultLabel.isDisplayed(), "Search result label is not displayed.");

	        
	        String expectedText = "Search result: \"\""; 
	        String actualText = searchResultLabel.getText().trim();
	        System.out.println("Actual Search Result Label Text: " + actualText);
	        Assert.assertEquals(actualText, expectedText, "Search result label text does not match expected.");
	        AdvantageExtent.createTest("Advantage Search Defect Check").log(Status.PASS, "Getting the results of the products for the empty search.");
	    }
	        
	 // TEST 4 : TestCase to check the search is displaying specific category products correctly   
	    
	    @Test (priority=3) //(enabled=false) 
	    public void SearchCheck() throws InterruptedException, IOException {
	        //String SearchInput = getProperty("SearchInput");
	        driver.get(url); // Use the stored URL
	        CPF = new ContactSearchPF(driver);
	        CPF.clickSearch();
	        CPF.enterSearchText(""); // Input search term
	        Thread.sleep(2000); // Wait for results to load
	        CPF.laptops().click(); // Click on laptops
	        
	        Thread.sleep(1000);
	        screenShot();
	        Thread.sleep(1000);
	        CPF.laptops().click();
	        Thread.sleep(1000);
	        CPF.headphones().click(); 
	        Thread.sleep(1000);
	        screenShot();
	        Thread.sleep(1000);
	        CPF.headphones().click();
	        Thread.sleep(1000);
	        CPF.tablets().click(); 
	        Thread.sleep(1000);
	        screenShot();
	        Thread.sleep(1000);
	        CPF.tablets().click();
	        Thread.sleep(1000);
	        CPF.speakers().click(); 
	        Thread.sleep(1000);
	        screenShot();
	        Thread.sleep(1000);
	        CPF.speakers().click();
	        Thread.sleep(1000);
	        CPF.mice().click(); 
	        Thread.sleep(1000);
	        screenShot();
	        Thread.sleep(1000);
	        CPF.mice().click();
	        Thread.sleep(1000);
	        screenShot();
	        AdvantageExtent.createTest("Advantage Selecting Specific categories").log(Status.PASS, "Specific category displayed correctly.");
	        
	        
	    }
	    
	    @AfterClass
	    public void teardown() throws InterruptedException {
	    	// Flushing the Extent Reports to save 
	        AdvantageExtent.getInstance().flush();
	        closeBrowser(); // Close the browser once after all tests
	    }



	   
	    
	    private String getProperty(String key) throws IOException {
	    	 FileInputStream fis = new FileInputStream(PROPPATH + "/AdvantageProperties.properties");
	        Properties prop = new Properties();
	        prop.load(fis);
	        return prop.getProperty(key);
	    }
	    
}
