package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSigninPF{

    WebDriver driver;

    // Constructor for initializing the PageFactory elements
    public AccountSigninPF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locator for the user link (Login button)
    @FindBy(id="menuUser")  // Replace with the correct id or locator strategy
    private WebElement userLink;

    // Locator for the username input field
    @FindBy(name = "username")  // Replace with the correct name or locator strategy
    private WebElement lUsername;

    // Locator for the password input field
    @FindBy(name = "password")  // Replace with the correct name or locator strategy
    private WebElement lPasswrd;

    // Locator for the sign-in button
    @FindBy(id = "sign_in_btn")  // Replace with the correct id or locator strategy
    private WebElement signinButton;


    // Getters for accessing these elements
    public WebElement getUserLink() {
        return userLink;
    }

    public WebElement getLUsername() {
        return lUsername;
    }

    public WebElement getLPasswrd() {
        return lPasswrd;
    }

    public WebElement getSigninButton() {
        return signinButton;
    }

    
}
