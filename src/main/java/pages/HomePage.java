package pages;
import commonfiles.AdvantageBaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage extends AdvantageBaseClass {
    private WebDriver driver;
    public WebDriverWait wait;

    // Selector Variables
    private By firstProductNameSelector = By.xpath("//p[@name='popular_item_16_name']");
    private By firstProductDetailsSelector = By.xpath("(//label[@id='details_16'])[1]");
    private By secondProductNameSelector = By.xpath("//p[@name='popular_item_10_name']");
    private By secondProductDetailsSelector = By.xpath("//label[@id='details_10']");
    private By thirdProductNameSelector = By.xpath("//p[@name='popular_item_21_name']");
    private By thirdProductDetailsSelector = By.xpath("//label[@id='details_21']");
    private By goUpButtonSelector = By.xpath("//img[@name='go_up_btn']");
    private By productTitleSelector = By.xpath("//h1[@class='roboto-regular screen768 ng-binding']");
    private By productTextSelector = By.xpath("//h1[@class='roboto-regular screen768 ng-binding']");
    private By popularItemSelector = By.xpath("//p[@name='popular_item_16_name']");
    private By seeOfferButtonSelector = By.xpath("//button[@id='see_offer_btn']");
    private By addToCartButtonSelector = By.xpath("(//button[normalize-space()='ADD TO CART'])[1]");
    private By shoppingCartLinkSelector = By.xpath("//a[@id='shoppingCartLink']");
    private By cartProductSelector = By.xpath("/html[1]/body[1]/header[1]/nav[1]/ul[1]/li[2]/ul[1]/li[1]/tool-tip-cart[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]/h3[1]");
    private By removeButtonSelector = By.cssSelector(".removeProduct.iconCss.iconX");
    private By updatedTextSelector = By.cssSelector(".center.roboto-medium.ng-scope");
    
    public By popularItemsCoordinateSelector = By.xpath("//h3[normalize-space()='POPULAR ITEMS']");
    public By popularItemsSelector = By.xpath("//a[normalize-space()='POPULAR ITEMS']");

    
    public By contactUsSelector = By.xpath("//a[normalize-space()='CONTACT US']");
    public By contactUsCoordinateSelector = By.xpath("//h1[normalize-space()='CONTACT US']");

    public By homeCoordinateSelector = By.xpath("//div[@id='speakersImg']"); //for initial coordinate of homepage
    public By homeSelector = By.xpath("//a[@ng-click='go_up()']"); //to verify navbar scroll on click
    
    public By specialOfferSelector = By.xpath("//a[normalize-space()='SPECIAL OFFER']");
    public By specialOfferCoordinateSelector = By.xpath("//h3[normalize-space()='SPECIAL OFFER']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public enum PageLinks {
        TABLETS("https://www.advantageonlineshopping.com/#/category/Tablets/3"),
        SPEAKERS("https://www.advantageonlineshopping.com/#/category/Speakers/4"),
        LAPTOPS("https://www.advantageonlineshopping.com/#/category/Laptops/1");

        private final String url;

        PageLinks(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    
    public void verifyProduct(String productLabel) {
        By productNameSelector;
        By productDetailsSelector;

        switch (productLabel) {
            case "First product":
                productNameSelector = firstProductNameSelector;
                productDetailsSelector = firstProductDetailsSelector;
                break;
            case "Second product":
                productNameSelector = secondProductNameSelector;
                productDetailsSelector = secondProductDetailsSelector;
                break;
            case "Third product":
                productNameSelector = thirdProductNameSelector;
                productDetailsSelector = thirdProductDetailsSelector;
                break;
            default:
                throw new IllegalArgumentException("Invalid product label: " + productLabel);
        }

        WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameSelector));
        String productName = productNameElement.getText();
        System.out.println(productLabel + " Name: " + productName);

        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productDetailsSelector));
        productLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitleSelector));

        String productPageTitle = driver.findElement(productTitleSelector).getText();
        Assert.assertEquals(productPageTitle, productName, productLabel + " name does not match!");

        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNameSelector));
    }
    // Selector Variables 

    


    public void navigateToHomePage() {
        driver.get("https://www.advantageonlineshopping.com/#/");
        driver.manage().window().maximize();
    }

    public Long getScrollPosition() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (Long) executor.executeScript("return window.pageYOffset;");
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public WebElement getScrollButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(goUpButtonSelector));
    }

    public void navigateToPopularItems() {
        try {
            Thread.sleep(4000);
            WebElement popularItemsLink = driver.findElement(popularItemsSelector);
            popularItemsLink.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(popularItemSelector));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyProduct(By productNameSelector, By productLinkSelector, String productLabel) {
        try {
            WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameSelector));
            String productName = productNameElement.getText();
            System.out.println(productLabel + " Name: " + productName);
            driver.findElement(productLinkSelector).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(productTitleSelector));
            String productPageTitle = driver.findElement(productTitleSelector).getText();
            Assert.assertEquals(productPageTitle, productName, productLabel + " name does not match!");

            driver.navigate().back(); // Navigate back to the popular items page
            wait.until(ExpectedConditions.visibilityOfElementLocated(productNameSelector)); // Wait for the product to be visible again

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openPageAndScroll() {
        driver.get("https://www.advantageonlineshopping.com/#/");
        WebElement specialOfferLink = wait.until(ExpectedConditions.elementToBeClickable(specialOfferSelector));
        clickElement(specialOfferLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(specialOfferSelector));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 700);");
    }

    public void verifyLink(WebElement element, String expectedUrl) {
        clickElement(element);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "The URL does not match the expected value.");
        System.out.println("URLs match: " + currentUrl);
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click(); // Retry click
        }
    }

    public int getElementYCoordinate(By by) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getLocation().getY();
    }

    public void clickAndVerifyLink(By linkBy, int expectedY) throws InterruptedException {
        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(linkBy));
        linkElement.click();
        
        // Wait for a moment for the page to settle after the click
        Thread.sleep(3000); // Wait for navigation or page load

        // After clicking, get the new Y-coordinate of the clicked link
        System.out.println("Y-coordinate before clicking: " + expectedY);

        int actualY = linkElement.getLocation().getY();
        System.out.println("Y-coordinate after clicking: " + actualY);
        
        // Check if the actual Y-coordinate is within the expected range
        Assert.assertTrue(isYWithinRange(actualY, expectedY), "Link is out of range! Expected Y: " + expectedY + ", Actual Y: " + actualY);
    }

    private boolean isYWithinRange(int actualY, int requiredY) {
        return (actualY >= requiredY - 200) && (actualY <= requiredY + 200);
    }

    public void mouseClickByLocator(By seeOfferButtonSelector2) throws InterruptedException {
        Thread.sleep(2000);
        WebElement el = driver.findElement(seeOfferButtonSelector2);
        Actions builder = new Actions(driver);
        builder.moveToElement(el).click(el).perform();
    }

    public void addProductToCartAndVerify() {
        try {

            Thread.sleep(5000); // Wait for the page to load

            // Click on the SPECIAL OFFER link
            mouseClickByLocator(specialOfferSelector);

            // Wait for the SEE OFFER button to be clickable and click it
            WebElement seeOfferButton = wait.until(ExpectedConditions.elementToBeClickable(seeOfferButtonSelector));
            seeOfferButton.click();

            // Sleep for 5 seconds to wait for the page to load
            Thread.sleep(5000);

            // Get the product text
            String productText = driver.findElement(productTextSelector).getText();

            // Click on the ADD TO CART button
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartButtonSelector));
            addToCartButton.click();

            // Hover over the shopping cart link
            WebElement shoppingCartLink = driver.findElement(shoppingCartLinkSelector);
            Actions actions = new Actions(driver);
            actions.moveToElement(shoppingCartLink).perform();

            // Sleep for 2 seconds to observe the hover effect
            Thread.sleep(2000);

            // Verify if the product was added to the cart
            WebElement cartProduct = driver.findElement(cartProductSelector);
            String cartProductText = cartProduct.getText();

            // Check if the added product matches the expected product
            if (productText.equals(cartProductText)) {
                System.out.println("Product successfully added to cart: " + cartProductText);
            } else {
                System.out.println("Product mismatch: Expected - " + productText + ", Found - " + cartProductText);
            }

            // Hover over the cart again to click remove
            actions.moveToElement(shoppingCartLink).perform();
            Thread.sleep(2000); // Wait for hover effect

            // Click on the Remove button
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(removeButtonSelector));
            removeButton.click();

            // Verify the text from the specified element after removal
            String updatedText = wait.until(ExpectedConditions.visibilityOfElementLocated(updatedTextSelector)).getText();
            System.out.println("Updated text after removing product: " + updatedText);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}