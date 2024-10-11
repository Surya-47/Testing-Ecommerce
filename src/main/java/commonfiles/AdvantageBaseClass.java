package commonfiles;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class AdvantageBaseClass {
    protected static WebDriver driver;

    // Browser initialization
    public static void invokeBrowser(String browser) {
        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            System.out.println("Browser not supported. Defaulting to Chrome.");
            driver = new ChromeDriver();
        }

        // Common setup for all browsers
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    
 // Method to click an element with retries
    public static void clickElementWithRetries(WebDriverWait wait, WebElement element, String elementName) {
        for (int attempt = 0; attempt < 5; attempt++) {
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader"))); // Ensure loader is gone
                element.click();
                System.out.println(elementName + " clicked successfully.");
                return; // Exit if the click is successful
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + " to click " + elementName + " failed: " + e.getMessage());
                try {
                    Thread.sleep(1000); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                // Recheck the visibility
                element = wait.until(ExpectedConditions.visibilityOf(element));
                scrollToElement(element);
            }
        }
        System.out.println("Failed to click " + elementName + " after multiple attempts.");
    }

    // Method to scroll to a specific element
    public static void scrollToElement(WebElement element) {
        // Scroll the element into view and adjust the scroll position
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        // Additional offset if needed
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);"); // Adjust the offset as necessary
    }

    // Screenshot method for taking screenshot
    public static void screenShot() throws IOException {
        File src2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src2, new File("./screenshot/" + "page-" + System.currentTimeMillis() + ".png"));
    }

    // Close browser
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
