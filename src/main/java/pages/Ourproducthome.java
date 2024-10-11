package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Ourproducthome {
    WebDriver driver;

    /// Constructor for driver
    public Ourproducthome(WebDriver driver) {
        this.driver = driver;
    }


  //for Test Case - 2
    By laptopGenreSelecting = By.xpath("//div[@id='laptopsImg']");
    By clickuserdropdown = By.xpath("//h4[@id='accordionAttrib1']");
    By selectcheckbox = By.id("operating_system_0");
    By chromelaptop = By.cssSelector("img[id='9']");
    
    //for Test Case - 3
    By headphonesgenreselect = By.xpath("//div[@id='headphonesImg']");
    
    
    //for Test Case - 4
    By headphoneimageselect = By.id("headphonesImg");
    By headphoneproductimage =  By.xpath("//img[@id='15']");

    ///return for the testcases
    
  //Returning for Test case - 2 
    public WebElement clickLaptopGenreSelect() {
        return driver.findElement(laptopGenreSelecting);
    }
    public WebElement clickDropDown() {
        return driver.findElement(clickuserdropdown);
    }
    public WebElement clickCheckBox() {
        return driver.findElement(selectcheckbox);
    }
    public WebElement clickChromeOsLaptop() {
        return driver.findElement(chromelaptop);
    }
    
    ///Hover Return Test - 3 
    
    public WebElement selectHeadphonesGenre() {
        return driver.findElement(headphonesgenreselect);
    }
    
    //Video Playback Test - 4
    public WebElement selectHeadphoneImage()
    {
    	return driver.findElement(headphoneimageselect);
    }
    public WebElement headphoneProduct()
    {
    	return driver.findElement(headphoneproductimage);
    }
}
