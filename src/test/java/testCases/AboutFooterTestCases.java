package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import commonfiles.AdvantageBaseClass;
import pages.AboutFooter;
import pages.AboutFooterPF;
import commonfiles.AdvantageExtent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AboutFooterTestCases extends AdvantageBaseClass {
    private AboutFooter adh;
    private AboutFooterPF pdh;
    private Properties prop;
    private String url;
    private static final String PROPPATH = System.getProperty("user.dir") + "/AdvantageProperties.properties";

    @Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional("firefox") String browser) throws IOException {
        // Set up ExtentReports using AdvantageExtent
        AdvantageExtent.getInstance().setSystemInfo("Browser", browser);
        AdvantageExtent.getInstance().setSystemInfo("Environment", "QA");

        // Load properties file
        FileInputStream fis = new FileInputStream(PROPPATH);
        prop = new Properties();
        prop.load(fis);
        url = prop.getProperty("url");

        // Initialize browser using the method from the base class
        invokeBrowser(browser);

        // Initialize the page objects for `AboutFooter` and `AboutFooterPF`
        adh = new AboutFooter(driver);
        pdh = new AboutFooterPF(driver);
    }

    @BeforeMethod
    public void navigateToHomePage() {
        if (driver != null) {
            driver.get(url);
        }
    }

    // Test-1: Facebook link verification
    @Test(priority = 0)
    public void testFacebookLink() throws InterruptedException, IOException {
        ExtentTest test = AdvantageExtent.createTest("Test-1: Verify Facebook Link");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        test.info("Navigated to the URL: " + url);

        WebElement facebookLink = pdh.getFacebookPageLink();
        wait.until(ExpectedConditions.elementToBeClickable(facebookLink));
        Assert.assertTrue(facebookLink.isDisplayed(), "Facebook link is not displayed");
        test.pass("Facebook link is displayed and clickable.");

        String originalWindow = driver.getWindowHandle();
        facebookLink.click();
        test.info("Clicked on the Facebook link.");

        Thread.sleep(2000);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        driver.close();
        test.info("New tab for Facebook was opened and closed successfully.");
        driver.switchTo().window(originalWindow);
        test.info("Switched back to the original window.");

        screenShot();
        test.info("Screenshot taken for Facebook link verification.");
    }

    // Test-2: Help Management page navigation
    @Test(priority = 1)
    public void testHelpManagementNavigation() throws InterruptedException, IOException {
        ExtentTest test = AdvantageExtent.createTest("Test-2: Verify Help Management Navigation");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        test.info("Navigated to the URL: " + url);

        WebElement homePageLink = adh.getHomePage();
        wait.until(ExpectedConditions.visibilityOf(homePageLink));
        Assert.assertTrue(homePageLink.isDisplayed(), "Home page link is not displayed");
        homePageLink.click();
        test.pass("Home page link is displayed and clicked.");

        WebElement helpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"helpMiniTitle\"]/label[1]")));
        Assert.assertTrue(helpButton.isDisplayed(), "Help button is not displayed");

        try {
            helpButton.click();
            test.pass("Help button clicked successfully.");
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", helpButton);
            test.info("Clicked on Help button using JavaScript.");
        }

        screenShot();
        test.info("Screenshot taken for Help Management navigation.");
    }

    // Test-3: Help Version page navigation
    @Test(priority = 2)
    public void testHelpVersionNavigation() throws InterruptedException, IOException {
        ExtentTest test = AdvantageExtent.createTest("Test-3: Verify Help Version Navigation");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        test.info("Navigated to the URL: " + url);

        WebElement homePageLink = adh.getHomePage1();
        wait.until(ExpectedConditions.elementToBeClickable(homePageLink));
        Assert.assertTrue(homePageLink.isDisplayed(), "Home page link is not displayed");
        homePageLink.click();
        test.pass("Home page link clicked.");

        WebElement helpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"helpMiniTitle\"]/label[2]")));
        Assert.assertTrue(helpButton.isDisplayed(), "Help version button is not displayed");
        helpButton.click();
        test.pass("Help version button clicked.");

        screenShot();
        test.info("Screenshot taken for Help Version navigation.");
        Thread.sleep(2000);
        
    }

    // Test-4: Help About page navigation
    @Test(priority = 3)
    public void testHelpAboutNavigation() throws InterruptedException, IOException {
        ExtentTest test = AdvantageExtent.createTest("Test-4: Verify Help About Navigation");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        test.info("Navigated to the URL: " + url);

        WebElement helpPageLink = adh.getHomePage2();
        wait.until(ExpectedConditions.elementToBeClickable(helpPageLink));
        Thread.sleep(2000);
        Assert.assertTrue(helpPageLink.isDisplayed(), "Help About page link is not displayed");
        helpPageLink.click();
        test.pass("Help About page link clicked.");
        Thread.sleep(2000);
        WebElement helpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"helpMiniTitle\"]/label[3]")));
        Assert.assertTrue(helpButton.isDisplayed(), "Help About button is not displayed");
        helpButton.click();
        test.pass("Help About button clicked.");

        screenShot();
        test.info("Screenshot taken for Help About navigation.");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void afterMethod() {
        // Any per-test cleanup can be done here, if necessary.
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        AdvantageExtent.getInstance().flush(); // Generate the report
    }
}
