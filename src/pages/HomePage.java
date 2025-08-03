package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
WebDriver driver;
JavascriptExecutor js;
String checkInDateString = "Sa 9 August";
String checkOutDateString = "Sa 23 August";
String state;
public HomePage(WebDriver theDriver) {
	this.driver=theDriver;
	js=(JavascriptExecutor)driver;
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
public void checkHomePageTest() {
	Assert.assertEquals(logInButtonIsDisplayed(), true);
}
public void fillData(String country) throws InterruptedException {
	driver.findElement(countrySearchLocator).sendKeys(country);
	Thread.sleep(2000);
	driver.findElement(dateSearchLocators).click();
    Thread.sleep(2000);
    driver.findElement(checkInDateLocator).click();
    Thread.sleep(2000);
    driver.findElement(checkOutDateLocator).click();
    Thread.sleep(2000);
    driver.findElement(searchButtonLocator).click();
    Thread.sleep(2000);
    Assert.assertEquals(checkTheFilterResult(), true);
    Thread.sleep(2000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(2000);
    driver.findElement(filterLocator).click();
    Thread.sleep(2000);
    driver.findElement(lowPriceFilter).click();
    Thread.sleep(1000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(1000);
    Assert.assertEquals(checkTheLowestPrice(), true);
    Thread.sleep(2000);
    js.executeScript("window.scrollTo(0,200)");
    Thread.sleep(2000);
    driver.findElement(filterLocator).click();
    Thread.sleep(2000);
    driver.findElement(highPriceFilter).click();
    Thread.sleep(000);
//    Assert.assertEquals(checkTheHighestPrice() , true);
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
	String firstPriceString=allPrices.get(0).getText().replace("JOD ","");
	String lastPriceString=allPrices.get(allPrices.size()-1).getText().replace("JOD ","");
	int firstPrice=Integer.parseInt(firstPriceString);
	int lastPrice=Integer.parseInt(lastPriceString);
	return firstPrice<lastPrice;
}

}
