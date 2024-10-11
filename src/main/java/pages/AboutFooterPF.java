package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutFooterPF {
    WebDriver driver;

    // Constructor to initialize WebDriver and PageFactory elements
    public AboutFooterPF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Using PageFactory annotation for the Facebook link element
    @FindBy(xpath = "/html/body/div[3]/footer/div/div/a[1]/img")
    private WebElement followButton;

    // Method to return the Facebook link WebElement (Test Case 1)
    public WebElement getFacebookPageLink() {
        return followButton;
    }
}
