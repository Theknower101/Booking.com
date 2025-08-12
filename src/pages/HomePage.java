package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
WebDriver driver;
JavascriptExecutor js;
String checkInDateString = "Sa 9 August";
String checkOutDateString = "Sa 23 August";
String state;
Actions action;
public HomePage(WebDriver theDriver) {
	this.driver=theDriver;
	this.js=(JavascriptExecutor)driver;
	this.action=new Actions(driver);
}
//Locators
By loginButtonLocator=By.cssSelector("a[aria-label='Register an account'] span[class='ca2ca5203b']");
By countrySearchLocator=By.name("ss");
By dateSearchLocators=By.xpath("//button[@data-testid='searchbox-dates-container']");
By checkInDateLocator=By.xpath("//span[@aria-label='Sa 9 August 2025']");
By checkOutDateLocator=By.xpath("//span[@aria-label='Sa 23 August 2025']");
By searchButtonLocator=By.cssSelector(".de576f5064.b46cd7aad7.ced67027e5.dda427e6b5.e4f9ca4b0c.ca8e0b9533.cfd71fb584.a9d40b8d51");
By filterLocator=By.xpath("//button[@data-testid='sorters-dropdown-trigger']");
By lowPriceFilter=By.cssSelector("button[aria-label='Price (lowest first)']");
By highPriceFilter=By.xpath("//button[@aria-label='Price (highest first)']");
By highestRatingFilterLocator=By.xpath("//button[@aria-label='Property rating (high to low)']");
By starCheckBoxLocator=By.xpath("//label[@for=':r34:']//div[contains(text(),'5 stars')]");
By highRateCheckBox=By.cssSelector("b7b2eb6274");
By signUpPopupCloseLocator = By.cssSelector("[role='dialog'] button[aria-label='Dismiss sign-in info.']");
public void checkHomePageTest() {
	Assert.assertEquals(logInButtonIsDisplayed(), true);
}
public void fillData(String country) throws InterruptedException, TimeoutException {
	 closeSignUpPopupIfExists();
	driver.findElement(countrySearchLocator).sendKeys(country);
	Thread.sleep(1000);
	driver.findElement(dateSearchLocators).click();
    Thread.sleep(1000);
    driver.findElement(checkInDateLocator).click();
    Thread.sleep(1000);
    driver.findElement(checkOutDateLocator).click();
    Thread.sleep(1000);
    driver.findElement(searchButtonLocator).click();
    Thread.sleep(1000);
    closeSignUpPopupIfExists();
    Assert.assertEquals(checkTheFilterResult(), true);
    Thread.sleep(2000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(1000);
    driver.findElement(filterLocator).click();
    closeSignUpPopupIfExists();
    Thread.sleep(1000);
    driver.findElement(lowPriceFilter).click();
    Thread.sleep(1000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(1000);
    Assert.assertEquals(checkTheLowestPrice(), true,"There is an error in Lowest Reviews filter");
    Thread.sleep(1000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(1000);
    driver.findElement(filterLocator).click();
    closeSignUpPopupIfExists();
    Thread.sleep(1000);
    driver.findElement(highPriceFilter).click();
    Thread.sleep(1000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(1000);
    Assert.assertEquals(checkTheHighestPrice() , true,"There is an error in highest price filter");
    Thread.sleep(1000);
    js.executeScript("window.scrollTo(0,200)");
    driver.findElement(filterLocator).click();
    closeSignUpPopupIfExists();
    Thread.sleep(1000);
    driver.findElement(highestRatingFilterLocator).click();
    Thread.sleep(1000);
    js.executeScript("window.scrollTo(0,2000)");
    Thread.sleep(1000);
   driver.findElement(By.xpath("//div[@data-filters-item='review_score:review_score=90']")).click();
    Thread.sleep(1000);
    Assert.assertEquals(verifyTheRate(), true);
    Thread.sleep(1000);
    WebElement sliderHandle = driver.findElement(By.className("b23ce1909f"));  // replace with correct handle selector
    action.clickAndHold(sliderHandle).moveByOffset(100, 0).release().perform();

    }
public boolean logInButtonIsDisplayed() {
	return driver.findElement(loginButtonLocator).isDisplayed();
}
public boolean checkTheFilterResult() {
	String inputValue=driver.findElement(countrySearchLocator).getAttribute("value");
	List<WebElement>allHotels=driver.findElements(By.className("d823fbbeed"));
	System.out.println(inputValue);
	System.out.println(allHotels.get(0).getText());
	return driver.getPageSource().contains(inputValue);
}
public boolean checkTheLowestPrice() {
	WebElement container=driver.findElement(By.className("d3ef0e3593"));
	List<WebElement>allPrices=container.findElements(By.xpath("//span[@data-testid='price-and-discounted-price']"));
	System.out.println(allPrices.get(0).getText());
	System.out.println(allPrices.get(allPrices.size()-1).getText());
	String firstPriceString=allPrices.get(0).getText().replace("JOD ","").replace(",", "");
	String lastPriceString=allPrices.get(allPrices.size()-1).getText().replace("JOD ","").replace(",", "");
	int firstPrice=Integer.parseInt(firstPriceString);
	int lastPrice=Integer.parseInt(lastPriceString);
	return firstPrice<lastPrice;
}
public boolean checkTheHighestPrice() {
	WebElement container=driver.findElement(By.className("d3ef0e3593"));
	List<WebElement>allPrices=container.findElements(By.xpath("//span[@data-testid='price-and-discounted-price']"));
	System.out.println(allPrices.get(0).getText());
	System.out.println(allPrices.get(allPrices.size()-1).getText());
	String firstPriceString=allPrices.get(0).getText().replace("JOD ","").replace(",", "");
	String lastPriceString=allPrices.get(allPrices.size()-1).getText().replace("JOD ","").replace(",", "");
	int firstPrice=Integer.parseInt(firstPriceString);
	int lastPrice=Integer.parseInt(lastPriceString);
	return firstPrice>lastPrice;
}
public void closeSignUpPopupIfExists() throws InterruptedException {
    List<WebElement> closeBtns = driver.findElements(By.cssSelector("button[aria-label='Dismiss sign-in info.']"));
    if (!closeBtns.isEmpty()) {
            js.executeScript("arguments[0].click();", closeBtns.get(0));
            System.out.println("Sign-up popup closed via JS click.");
            Thread.sleep(500);
    } else {
        System.out.println("No sign-up popup found.");
    }
}
public boolean verifyTheRate() {
	List<WebElement> rates=driver.findElements(By.className("f63b14ab7a"));
    double rate=Double.parseDouble(rates.get(0).getText().replace("Exceptional ",""));
return rate>9;
}
}
 