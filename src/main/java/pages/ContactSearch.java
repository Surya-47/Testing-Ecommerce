package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class ContactSearch {
	
	WebDriver driver;
	public ContactSearch(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By Home= By.linkText("HOME");
	By ContactUs = By.linkText("CONTACT US");
	By Search = By.id("menuSearch");
	By SearchText=By.id("autoComplete");
	By ChatBot = By.cssSelector("div[name='chat_with_us']");
	By SelectCategory=By.xpath("//select[@name='categoryListboxContactUs']");
	By SelectProduct=By.xpath("//select[@name='productListboxContactUs']");
	By Email=By.xpath("//input[@name='emailContactUs']");
	By Subject=By.cssSelector("textarea[name='subjectTextareaContactUs']");
	By Send=By.id("send_btn");
	By ViewAll=By.linkText("View All");
	By Continue=By.xpath("//a[@class='a-button ng-binding']");
	
    

	
	//Create methods
	public WebElement Home()
	{
		return driver.findElement(Home);
	}
	
	public WebElement ContactUs()
	{
		return driver.findElement(ContactUs);
	}
	
	public WebElement Search()
	{
		
		return driver.findElement(Search);
	}
	
	public WebElement SearchText()
	{
		
		return driver.findElement(SearchText);
	}
	
	public WebElement ChatBot()
	{
		
		return driver.findElement(ChatBot);
	}
	
	public WebElement SelectCategory()
	{
		
		return driver.findElement(SelectCategory);
	}
	
	public WebElement SelectProduct()
	{
		
		return driver.findElement(SelectProduct);
	}
	
	public WebElement Email()
	{
		
		return driver.findElement(Email);
	}
	
	public WebElement Subject()
	{
		
		return driver.findElement(Subject);
	}
	
	public WebElement Send()
	{
		
		return driver.findElement(Send);
	}
	
	public WebElement ViewAll()
	{
		
		return driver.findElement(ViewAll);
	}
	
	public WebElement Continue()
	{
		
		return driver.findElement(Continue);
	}

}
