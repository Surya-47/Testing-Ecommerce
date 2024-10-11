package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactSearchPF {
	
	WebDriver driver;
    public ContactSearchPF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "menuSearch") // Search button
    private WebElement search;

    @FindBy(id = "autoComplete") // Search text input
    private WebElement searchText;

    @FindBy(xpath = "//div[@class='option']//div[1]//div[1]//input[1]") // Laptops
    private WebElement laptops;

    @FindBy(xpath = "//div[2]//div[1]//input[1]") // Headphones
    private WebElement headphones;

    @FindBy(xpath = "//div[@class='noPromotedProductDiv']//div[3]//div[1]//input[1]") // Tablets
    private WebElement tablets;

    @FindBy(xpath = "//div[4]//div[1]//input[1]") // Speakers
    private WebElement speakers;

    @FindBy(xpath = "//div[5]//div[1]//input[1]") // Mice
    private WebElement mice;

    

    public void clickSearch() {
        search.click();
    }

    public void enterSearchText(String text) {
        searchText.clear(); // Clear the input field before entering text
        searchText.sendKeys(text);
    }

    public WebElement laptops() {
        return laptops;
    }

    public WebElement headphones() {
        return headphones;
    }

    public WebElement tablets() {
        return tablets;
    }

    public WebElement speakers() {
        return speakers;
    }

    public WebElement mice() {
        return mice;
    }


}
