package testCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commonfiles.AdvantageBaseClass;
import commonfiles.AdvantageExtent;
import pages.AccountSignin;
import pages.AccountSigninPF;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AccountSignInTestCases extends AdvantageBaseClass {

    WebDriver driver;
    AccountSignin adh;
    AccountSigninPF pdh;
    private static final String PROPPATH = System.getProperty("user.dir") + "/AdvantageProperties.properties";
   

    // Method to initialize WebDriver based on browser parameter
    public void initializeDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("Browser not supported! Defaulting to Chrome.");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Method to load properties from file
    public Properties loadProperties() throws IOException {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPPATH);) {
            prop.load(fis);
        }
        return prop;
    }

    // Common method to initialize WebDriver and navigate to the URL
    public void navigateToURL(String browser, String url) throws IOException {
        initializeDriver(browser);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe(url)); // Wait until the URL is fully loaded
        adh = new AccountSignin(driver);
        pdh = new AccountSigninPF(driver);
        AdvantageExtent.getInstance(); 
    }

    // Test case for verifying redirection of the user link
    @Test(priority = 1)
    @Parameters({"browser"})
    public void testUserLinkRedirection(String browser) throws IOException {
        Properties prop = loadProperties();
        navigateToURL(browser, prop.getProperty("url"));

        WebElement userLink = pdh.getUserLink();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(userLink)).click();

        String expectedTitle = "Advantage Shopping";
        String actualTitle = driver.getTitle().replace("\u00A0", "").trim();
        Assert.assertEquals(actualTitle, expectedTitle, "Webpage title matches.");

        AdvantageExtent.createTest("User redirection").log(Status.PASS, "user redirection sucessfull");


        driver.quit();
    }

  //login and logout functionality
  	@Test(priority=2)
  	@Parameters({"browser"}) // Parameterize browser choice
  	public void testloginandlogout(String browser) throws InterruptedException, IOException {
  		// Initialize WebDriver based on the browser parameter
  		initializeDriver(browser);

  		// Load properties from config.properties file
  		Properties prop = new Properties();
  		try (FileInputStream fis = new FileInputStream("AdvantageProperties.properties")) {
  			prop.load(fis);
  		}

  		// Fetch data from properties file
  		String uname = prop.getProperty("username");
  		String pwd = prop.getProperty("passwrd");
  		String url = prop.getProperty("url");

  		// Use WebDriverWait to ensure URL navigation waits until the browser is ready (explicit wait is used)
  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

  		// Navigate to the URL
  		driver.get(url);
  		wait.until(ExpectedConditions.urlToBe(url)); // Wait until the browser successfully loads the URL

  		// Initialize Page Object classes
  		adh = new AccountSignin(driver);

  		// Click the login button first
  		WebElement userLink = adh.getUserLink(); // Assume you have a locator method for the user button
  		wait.until(ExpectedConditions.elementToBeClickable(userLink));
  		userLink.click();

  		// Now enter the email and password
  		Thread.sleep(3000);
  		wait.until(ExpectedConditions.visibilityOf(adh.getLUsername()));// Wait until username field is visible
  		//validate the visibility of the username field
  		Assert.assertTrue(adh.getLUsername().isDisplayed(), "Username field is not visible.");
  		adh.getLUsername().sendKeys(uname);      
  		Thread.sleep(3000);
  		wait.until(ExpectedConditions.visibilityOf(adh.getLPassword()));
  		//validate the visibility of the password field
  		Assert.assertTrue(adh.getLPassword().isDisplayed(), "Password field is not visible.");
  		adh.getLPassword().sendKeys(pwd);

  		// Now click the login button after entering the email and password
  		WebElement signinButton = adh.getSigninButton();
  		wait.until(ExpectedConditions.visibilityOf(signinButton)); // Ensure it's visible
  		wait.until(ExpectedConditions.elementToBeClickable(signinButton)); // Ensure it's clickable

  		// Optionally, scroll into view and click using JavaScript
  		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signinButton);
  		((JavascriptExecutor) driver).executeScript("arguments[0].click();", signinButton);

  		//logout functionality
  		//go to user menu
  		Thread.sleep(1000);
  		wait.until(ExpectedConditions.elementToBeClickable(userLink));
  		userLink.click();
  		Thread.sleep(1000);

  		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/ul[1]/li[3]/a[1]/div[1]/label[3]"));
  		String display = element.getCssValue("display");

  		//check to see if the element is hidden or not
  		if (display.equals("none")) {
  			System.out.println("Element is hidden.");
  		} else {
  			element.click();
  		}

  		// Assert user is logged out and redirected to the login page
  		wait.until(ExpectedConditions.visibilityOf(adh.getUserLink())); // Login button should reappear
  		Assert.assertTrue(adh.getUserLink().isDisplayed(), "User is not redirected to login page after logout.");

  		Thread.sleep(2000); 
  		AdvantageExtent.createTest("Login and Logout").log(Status.PASS, "Login and logout successfully.");

  		//close the driver
  		driver.close();        
  	}



    // Test case for checking redirection of the create new user link
    @Test(priority = 3)
    @Parameters({"browser"})
    public void testCreateNewUserRedirection(String browser) throws IOException {
        Properties prop = loadProperties();
        navigateToURL(browser, prop.getProperty("url"));

        WebElement userLink = adh.getUserLink();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(userLink)).click();

        WebElement createLink = adh.getCreateLink();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(createLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createLink);

        Assert.assertNotEquals(driver.getCurrentUrl(), prop.getProperty("url"), "URL did not change successfully.");
        AdvantageExtent.createTest("Redirection of create new user").log(Status.PASS, "redirection of create new user is successfull.");
        driver.quit();
    }

    // Test case for checking the functionality of the sign-in with Facebook button
    @Test(priority = 4)
    @Parameters({"browser"})
    public void testSignInWithFacebook(String browser) throws IOException {
        Properties prop = loadProperties();
        navigateToURL(browser, prop.getProperty("url"));

        WebElement userLink = adh.getUserLink();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(userLink)).click();

        WebElement fbLink = adh.getFbLink();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(fbLink)).click();

        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".or.center.invalid")));
        Assert.assertEquals(error.getText(), 
                "403 FORBIDDEN Sorry, connecting to Facebook is currently unavailable. Please try again later.",
                "Page message does not match the expected value.");

        AdvantageExtent.createTest("Sign in with facebook").log(Status.PASS, "Sign-in with facebook not available");
        driver.quit();
    }

  //creating new user
  	@Test(priority=5)
  	@Parameters({"browser"}) // Parameterize browser choice
  	public void testCreateNewUser(@Optional("chrome") String browser) throws InterruptedException, IOException {
  		// Initialize WebDriver based on the browser parameter
  		initializeDriver(browser);

  		// Load properties from config.properties file
  		Properties prop = new Properties();
  		try (FileInputStream fis = new FileInputStream("AdvantageProperties.properties")) {
  			prop.load(fis);
  		}

  		// Fetch data from properties file
  		String url = prop.getProperty("url");
  		String uname = prop.getProperty("username");
  		String email = prop.getProperty("email");
  		String pwd = prop.getProperty("passwrd");
  		String cpwd = prop.getProperty("confirmPasswrd");

  		// Use WebDriverWait to ensure URL navigation waits until the browser is ready (explicit wait is used)
  		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

  		// Navigate to the URL
  		driver.get(url);
  		wait.until(ExpectedConditions.urlToBe(url)); // Wait until the browser successfully loads the URL

  		// Initialize Page Object classes
  		adh = new AccountSignin(driver);

  		// Click the login button first
  		WebElement userLink = adh.getUserLink(); // Assume you have a locator method for the user button
  		wait.until(ExpectedConditions.elementToBeClickable(userLink)); // Wait until clickable
  		userLink.click();

  		// Add an implicit wait (optional) to ensure transitions/animations are complete
  		Thread.sleep(3000); // 3 seconds pause to handle UI transitions or animations

  		// Click the create new account link
  		WebElement createLink = adh.getCreateLink();
  		wait.until(ExpectedConditions.visibilityOf(createLink)); // Ensure visibility
  		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createLink); // Scroll to the element

  		// Additional check for any overlay or popup
  		try {
  			// Check if there's any overlay or modal to close
  			WebElement closeOverlay = driver.findElement(By.xpath("/html/body/login-modal/div/div/div[3]")); // Replace with actual overlay locator
  			if (closeOverlay.isDisplayed()) {
  				closeOverlay.click();
  			}
  		} catch (NoSuchElementException e) {
  			// No overlay found, proceed
  		}

  		// Ensure the element is clickable
  		wait.until(ExpectedConditions.elementToBeClickable(createLink));

  		// Try clicking using JavaScript (fallback)
  		try {
  			createLink.click(); // Regular click
  		} catch (ElementClickInterceptedException e) {
  			((JavascriptExecutor) driver).executeScript("arguments[0].click();", createLink); // Fallback to JS click
  		}

  		//get all the necessary details
  		wait.until(ExpectedConditions.visibilityOf(adh.getCUsername())); // Wait until username field is visible
  		adh.getCUsername().sendKeys(uname);
  		adh.getCUsername().sendKeys(Keys.ENTER);
  		Thread.sleep(5000);
  		adh.getEmailField().sendKeys(email);
  		adh.getEmailField().sendKeys(Keys.ENTER);
  		Thread.sleep(5000);
  		adh.getPasswordField().sendKeys(pwd);
  		adh.getPasswordField().sendKeys(Keys.ENTER);
  		Thread.sleep(5000);
  		adh.getConfirmPasswordField().sendKeys(cpwd);
  		adh.getConfirmPasswordField().sendKeys(Keys.ENTER);
  		Thread.sleep(6000);

  		WebElement chkbox=adh.getAgreeCheckbox();
  		chkbox.click();
  		//to validate if the checkbox is selected
  		Assert.assertTrue(chkbox.isSelected(), "Terms and conditions checkbox is not selected.");

  		Thread.sleep(1000);
  		WebElement registerButton = adh.getRegisterButton();
  		//to validate if the register button is displayed
  		Assert.assertTrue(registerButton.isDisplayed(), "Register button is not visible.");
  		registerButton.click();

  		Thread.sleep(3000);

  		//Validate if user already exists
  		WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center.block.smollMargin.invalid")));
  		String message=msg.getText();
  		System.out.println(message);
  		//close the webpage
  		driver.close(); 


  	}

    	@AfterClass
    public void teardown() throws InterruptedException {
    	// Flushing the Extent Reports to save 
        AdvantageExtent.getInstance().flush();
        closeBrowser(); // Close the browser once after all tests
    }

  }

