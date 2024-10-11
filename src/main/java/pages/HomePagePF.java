package pages;

import commonfiles.AdvantageBaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePagePF extends AdvantageBaseClass {
    private WebDriver driver;
    public WebDriverWait wait;

    // WebElement definitions using FindBy annotations
    @FindBy(xpath = "(//button[@name='explore_now_btn'][normalize-space()='EXPLORE NOW'])[1]")
    private WebElement exploreNowButton1;

    @FindBy(xpath = "(//button[@name='explore_now_btn'][normalize-space()='EXPLORE NOW'])[2]")
    private WebElement exploreNowButton2;

    @FindBy(xpath = "(//button[@name='explore_now_btn'][normalize-space()='EXPLORE NOW'])[3]")
    private WebElement exploreNowButton3;

    @FindBy(css = "div[class='uiview ng-scope'] span:nth-child(2)")
    private WebElement speakerElement;

    @FindBy(css = "div.uiview.ng-scope span:nth-child(3)")
    private WebElement laptopElement;

    public HomePagePF(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout for waits
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://www.advantageonlineshopping.com/#/");
        driver.manage().window().maximize();
    }

    public WebElement getExploreNowButton1() {
        return wait.until(ExpectedConditions.elementToBeClickable(exploreNowButton1));
    }

    public WebElement getExploreNowButton2() {
        return wait.until(ExpectedConditions.elementToBeClickable(exploreNowButton2));
    }

    public WebElement getExploreNowButton3() {
        return wait.until(ExpectedConditions.elementToBeClickable(exploreNowButton3));
    }

    public WebElement getSpeakerElement() {
        return wait.until(ExpectedConditions.elementToBeClickable(speakerElement));
    }

    public WebElement getLaptopElement() {
        return wait.until(ExpectedConditions.elementToBeClickable(laptopElement));
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void verifyLink(WebElement element, String expectedUrl) {
        element.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "URL did not match!");
    }
}
