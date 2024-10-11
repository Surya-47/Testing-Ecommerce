package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OurproductshomePF {
    WebDriver driver;

    /// Constructor for driver
    public OurproductshomePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // for Test Case - 1
    @FindBy(linkText = "OUR PRODUCTS") public WebElement ourElement;
    @FindBy(id = "speakersImg") public WebElement speakerGenreSelect;
    @FindBy(xpath = "//img[@id='25']") public WebElement speakerItem;
    @FindBy(xpath = "//button[@name='save_to_cart']") public WebElement speakerAddToCart;
    
    public WebElement clickSpeakerGenre() {
        return speakerGenreSelect;
    }

    public WebElement clickSpeakerItem() {
        return speakerItem;
    }

    public WebElement clickAddSpeakerToCart() {
        return speakerAddToCart;
    }
}