package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelsPage {
WebDriver driver;
public HotelsPage(WebDriver theDriver) {
	this.driver=theDriver;
}
public void reserveHotel() throws InterruptedException {
   List<WebElement> hotels = driver.findElements(By.cssSelector("div[data-testid='property-card']"));
   hotels.get(0).click();
    Thread.sleep(1000);
   for (String windowHandle : driver.getWindowHandles()) {
       driver.switchTo().window(windowHandle);
   }

   driver.findElement(By.xpath("//button[@id='hp_book_now_button']")).click();
    Thread.sleep(1000);
   driver.findElement(By.xpath("//button[@data-bui-component='Popover']")).click();
   Thread.sleep(5000);
}
}
