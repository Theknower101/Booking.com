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
	WebElement container=driver.findElement(By.className("d3ef0e3593"));
   List<WebElement> hotels = container.findElements(By.xpath("//div[@data-testid='title']"));
   System.out.println(hotels.get(0).getText());
   Thread.sleep(1000);
   hotels.get(0).click();
   for (String windowHandle : driver.getWindowHandles()) {
       driver.switchTo().window(windowHandle);
   }
   Thread.sleep(1000);
   driver.findElement(By.xpath("//button[@id='hp_book_now_button']")).click();
    Thread.sleep(1000);
   driver.findElement(By.xpath("//button[@data-bui-component='Popover']")).click();
   Thread.sleep(5000);
}
}

