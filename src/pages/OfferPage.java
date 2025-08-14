package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class OfferPage {
	WebDriver driver;
	JavascriptExecutor js;
public OfferPage(WebDriver theDriver) {
	this.driver=theDriver;
	this.js=(JavascriptExecutor)theDriver;
}
public void enterToOfferPage() throws InterruptedException {
	driver.navigate().to("https://www.booking.com/index.en-gb.html?label=gen173nr-10CAEoggI46AdIM1gEaHSIAQGYATO4ARfIAQzYAQPoAQH4AQGIAgGoAgG4Atq498QGwAIB0gIkNjQyYzRhY2YtZWZkZi00OWY5LWFlYTctZDAzNTlmMjA2M2E22AIB4AIB&sid=d62041c7b890481892c138019123c19c&keep_landing=1&sb_price_type=total&");
	Thread.sleep(1000);
	js.executeScript("window.scrollTo(0,600)");
	driver.findElement(By.xpath("//a[@data-testid='promotional-banner-content-cta']")).click();
	Assert.assertEquals(verifyTheOfferPage(), true);
}
public boolean verifyTheOfferPage() {
	WebElement offerText=driver.findElement(By.className("ec8e078fab"));
	return offerText.isDisplayed();
}
}
