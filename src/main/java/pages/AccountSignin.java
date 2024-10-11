package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountSignin {
    private WebDriver driver;

    // Constructor to initialize WebDriver
    public AccountSignin(WebDriver driver) {
        this.driver = driver;
    }
    
    // Locators
    private By userLink = By.cssSelector("#menuUser");
    private By registerButton = By.id("register_btn");
    private By createLink = By.cssSelector("body > login-modal > div > div > div.login.ng-scope > a.create-new-account.ng-scope");
    private By cUsername = By.name("usernameRegisterPage");
    private By email = By.name("emailRegisterPage");
    private By password = By.xpath("/html/body/div[3]/section/article/sec-form/div[1]/div[2]/div/div[1]/div[2]/sec-view[1]/div/input");
    private By confirmPassword = By.xpath("/html/body/div[3]/section/article/sec-form/div[1]/div[2]/div/div[1]/div[2]/sec-view[2]/div/input");
    private By agreeCheckbox = By.cssSelector("#formCover > sec-view > div > input");
    private By fbLink = By.xpath("/html[1]/body[1]/login-modal[1]/div[1]/div[1]/div[3]/span[1]");
    private By signoutButton = By.xpath("//*[@id='loginMiniTitle']/label[3]");
    private By signinButton = By.id("sign_in_btn");
    private By lUsername = By.xpath("/html[1]/body[1]/login-modal[1]/div[1]/div[1]/div[3]/sec-form[1]/sec-view[1]/div[1]/input[1]");
    private By lPassword = By.xpath("//input[@name='password']");

    // Methods to return WebElement objects

    /**
     * Returns the user link WebElement.
     */
    public WebElement getUserLink() {
        return driver.findElement(userLink);
    }

    /**
     * Returns the create account link WebElement.
     */
    public WebElement getCreateLink() {
        return driver.findElement(createLink);
    }

    /**
     * Returns the register button WebElement.
     */
    public WebElement getRegisterButton() {
        return driver.findElement(registerButton);
    }

    /**
     * Returns the username field WebElement for registration.
     */
    public WebElement getCUsername() {
        return driver.findElement(cUsername);
    }

    /**
     * Returns the email field WebElement for registration.
     */
    public WebElement getEmailField() {
        return driver.findElement(email);
    }

    /**
     * Returns the password field WebElement for registration.
     */
    public WebElement getPasswordField() {
        return driver.findElement(password);
    }

    /**
     * Returns the confirm password field WebElement for registration.
     */
    public WebElement getConfirmPasswordField() {
        return driver.findElement(confirmPassword);
    }

    /**
     * Returns the agreement checkbox WebElement for registration.
     */
    public WebElement getAgreeCheckbox() {
        return driver.findElement(agreeCheckbox);
    }

    /**
     * Returns the Facebook link WebElement.
     */
    public WebElement getFbLink() {
        return driver.findElement(fbLink);
    }

    /**
     * Returns the sign-out button WebElement.
     */
    public WebElement getSignoutButton() {
        return driver.findElement(signoutButton);
    }

    /**
     * Returns the sign-in button WebElement.
     */
    public WebElement getSigninButton() {
        return driver.findElement(signinButton);
    }

    /**
     * Returns the username field WebElement for login.
     */
    public WebElement getLUsername() {
        return driver.findElement(lUsername);
    }

    /**
     * Returns the password field WebElement for login.
     */
    public WebElement getLPassword() {
        return driver.findElement(lPassword);
    }
}
