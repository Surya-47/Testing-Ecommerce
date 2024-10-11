package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AboutFooter {
	WebDriver driver;

	public AboutFooter(WebDriver driver) {
        this.driver = driver;
    }
    

    //for Test Case - 2
    By HelpButton= By.xpath("//*[@id=\"menuHelp\"]");
    By VersionPage= By.cssSelector("#helpMiniTitle > label:nth-child(3)");
    
    //for Test Case - 3
    By HelpButton1= By.xpath("//*[@id=\"menuHelp\"]");
    By AboutPage = By.cssSelector("#helpMiniTitle > label:nth-child(2)");
    
    //for Test Case - 4
    By HelpButton2= By.xpath("//*[@id=\"menuHelp\"]");
    By ManagementPage = By.cssSelector("#helpMiniTitle");
  
    
    //Returning for Test case - 2 
    public WebElement getVersionPage() {
        return driver.findElement(VersionPage);
    }
    public WebElement getHomePage() {
        return driver.findElement(HelpButton);
    }
    
    // Returning for  Test case - 3 
    
    public WebElement getAboutPage() {
        return driver.findElement(AboutPage);
    }
    public WebElement getHomePage1() {
        return driver.findElement(HelpButton1);
    }
    //Returning for Test case-4
    public WebElement getManagementPage() {
        return driver.findElement(ManagementPage);
    }
    public WebElement getHomePage2() {
        return driver.findElement(HelpButton2);
    }
}

