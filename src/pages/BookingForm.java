package pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BookingForm {
WebDriver driver;
Random rand=new Random();
public BookingForm(WebDriver theDriver) {
	this.driver=theDriver;
}
public void fillBookingForm() throws InterruptedException {
	 for (String winHandle : driver.getWindowHandles()) {
         driver.switchTo().window(winHandle);
     }

     driver.findElement(By.name("firstname")).sendKeys("Linda");
     Thread.sleep(500);
     driver.findElement(By.name("lastname")).sendKeys("alelayyan");
     Thread.sleep(500);
     driver.findElement(By.name("email")).sendKeys("test123@gmail.com");
     Thread.sleep(500);
    Select mySelect=new Select(driver.findElement(By.className("ed4d3c8194")));
    mySelect.selectByIndex(rand.nextInt(1,10));
     Thread.sleep(500);
     driver.findElement(By.name("phoneNumber")).sendKeys("079999999");

     driver.findElement(By.cssSelector("button[type='submit']")).click();
}
}
