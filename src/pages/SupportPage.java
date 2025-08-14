package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SupportPage {
	WebDriver driver;
public SupportPage(WebDriver theDriver) {
	this.driver=theDriver;
}
public void enterToSupport() {
	driver.navigate().to("https://secure.booking.com/help?aid=304142&label=gen173nr-10CAEoggI46AdIM1gEaHSIAQGYATO4ARfIAQzYAQPoAQH4AQGIAgGoAgG4Aq2O98QGwAIB0gIkZjM1NGMzMzAtZjdmMy00NWM5LTkwMmYtZWNkMDFjMzI3YzQy2AIB4AIB&sid=ae010459fc3152e5eb9a82794104aec8");
	Assert.assertEquals(verifyTheSupportPage() , true);
}
public boolean verifyTheSupportPage() {
	WebElement welcomeSupport=driver.findElement(By.xpath("//h1[@class='f2d726909b f546354b44']//div[@class='f6e3a11b0d ae5dbab14d e95943ce9b']"));
	return welcomeSupport.isDisplayed();
}
}
