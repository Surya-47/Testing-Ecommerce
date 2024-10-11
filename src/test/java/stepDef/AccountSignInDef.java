package stepDef;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class AccountSignInDef {

	WebDriver driver;
	WebDriverWait wait;

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		// Initialize the ChromeDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Navigate to the login page
		driver.get("https://www.advantageonlineshopping.com/");
	}

	@When("I sign in using username {string} and password {string}")
	public void i_sign_in_using_valid_username_and_password(String username, String password) throws InterruptedException {
		// Click the user icon to open the login modal
		WebElement userLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#menuUser")));
		userLink.click();

		// Wait for the username field to be present and send the username
		WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//input[@name='username']"))); // Adjusted XPath
		usernameField.sendKeys(username);
		System.out.println("after user");
		Thread.sleep(1000);
		System.out.println("after sleep");
		// Wait for the password field to be present and send the password
		WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='password']"))); // Adjusted XPath
		//        passwordField.sendKeys("Qwerty@123");
		passwordField.sendKeys(password);
		//
		Thread.sleep(1000);
		System.out.println("after pass sleep");
		passwordField.sendKeys(Keys.ENTER);
		System.out.println("after sign-in btn ck");
		//        signInButton.click();
	}


	@When("I sign in again")
	public void i_sign_in_again() {
		//WebElement userLink = driver.findElement(By.cssSelector("#menuUser"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement userLink=wait.until(ExpectedConditions.elementToBeClickable(By.id("menuUserLink")));
		userLink.click();
		System.out.println("sigin again ");
	}

	@When("I navigate to {string}")
	public void i_navigate_to(String section) {
		if (section.equals("My Account")) {
			WebElement myAccountLink = driver.findElement(By.xpath("//*[@id=\"loginMiniTitle\"]/label[1]"));
			wait.until(ExpectedConditions.elementToBeClickable(myAccountLink)).click();
		}
		System.out.println("I navigate to {string}");
	}

	@When("I click on {string}")
	public void i_click_on(String action) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement deleteAccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"myAccountContainer\"]/div[6]/button/div")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteAccountLink);
		if (action.equals("Delete Account")) {
			wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)).click();
		}
		System.out.println("I click on {string}");
	}

	@When("I confirm the deletion by clicking {string}")
	public void i_confirm_the_deletion_by_clicking(String confirmation) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement yesButton = driver.findElement(By.cssSelector("#deleteAccountPopup > div.deleteBtnContainer > div.deletePopupBtn.deleteRed"));
		wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
		System.out.println("I confirm the deletion by clicking {string}");
	}

	@Then("the account should be deleted successfully")
	public void the_account_should_be_deleted_successfully() {
		
		System.out.println("the account should be deleted successfully");
	
	}
}
