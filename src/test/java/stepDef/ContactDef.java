package stepDef;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class ContactDef {
WebDriver driver;
	 
	
	
	@Given("I am on the homepage")
	public void i_am_on_the_homepage() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.advantageonlineshopping.com/#/");
		String actualTitle = driver.getTitle().replace("\u00A0", "").trim();
        System.out.println("Actual Title: " + actualTitle);

        // Assert that the page title is "Advantage Shopping"
        Assert.assertEquals("Advantage Shopping", actualTitle);
		System.out.println("User is on Advantage Shopping Home Page");
	}

	@When("I click on the Contact Us button")
	public void i_click_on_the_contact_us_button() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));

        // Find the Contact Us element
        WebElement contactUsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("CONTACT US")));
        scrollToElement(contactUsButton);
        Thread.sleep(500); // Brief wait to ensure the scroll is completed

        // Click the Contact Us button with retries
        clickElementWithRetries(wait, contactUsButton, "Contact Us");
	}
		
	

	
		@When("I click on the ChatBot")
		public void i_click_on_the_chat_bot() throws InterruptedException {
		    // Wait for the ChatBot button to be visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement chatBotButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[name='chat_with_us']")));
		    scrollToElement(chatBotButton);
		    Thread.sleep(500); // Wait for scroll to complete

		    // Click the ChatBot button
		    chatBotButton.click();
		    
		    // Store the current window handle
		    String mainWindowHandle = driver.getWindowHandle();
		    
		    // Wait for the new window to open
		    for (String windowHandle : driver.getWindowHandles()) {
		        if (!windowHandle.equals(mainWindowHandle)) {
		            driver.switchTo().window(windowHandle);
		            break;
		        }
		    }
		}

		@Then("I enter the chatbot text")
		public void i_enter_the_chatbot_text() throws InterruptedException {
		    // Wait for the ChatBot page to load
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		    // Wait for the URL to confirm we are on the ChatBot page
		    wait.until(ExpectedConditions.urlToBe("https://www.advantageonlineshopping.com/chat.html"));
		    
		    String currentUrl = driver.getCurrentUrl();
	        Assert.assertEquals("https://www.advantageonlineshopping.com/chat.html", currentUrl);
	        
	        String chatbotTitle = driver.getTitle().trim();
	        System.out.println("ChatBot Page Title: " + chatbotTitle);
	        Assert.assertEquals("Advantage Online Shopping Demo Support Chat", chatbotTitle); 

		    // Wait for the "Server connect." message to be visible
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Server connect.']")));

		    // Now wait for the text message input field to be visible
		    WebElement textMessageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("textMessage")));
		    
		    // Now wait for the send button to be visible
		    WebElement sendButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSender")));

		    // Define the messages to be sent
		    String[] messages = {
		        "Hello",
		        "Sachin",
		        "Yes",
		        "Yes",
		        "HeadPhones",
		        "No",
		        "Yes",
		        "ygfyf6564bh",
		        "I need to add one more category also.",
		        "But I need to add one more category."
		    };

		    // Loop through the messages and send each one
		    for (String message : messages) {
		        // Ensure the input field is ready for interaction
		        wait.until(ExpectedConditions.elementToBeClickable(textMessageInput));
		        textMessageInput.clear(); // Clear the input field before sending a new message
		        textMessageInput.sendKeys(message); // Type the message
		        
		        // Wait for the send button to be clickable
		        wait.until(ExpectedConditions.elementToBeClickable(sendButton));
		        sendButton.click(); // Click the send button
		        
		        // Wait for some time before sending the next message
		        Thread.sleep(6000); 
		    }
		    driver.quit();
		}




	public void clickElementWithRetries(WebDriverWait wait, WebElement element, String elementName) {
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
    public  void scrollToElement(WebElement element) {
        // Scroll the element into view and adjust the scroll position
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        // Additional offset if needed
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);"); // Adjust the offset as necessary
    }




}
