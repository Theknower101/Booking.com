package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
private static WebDriver driver;
private static JavascriptExecutor js;
public static WebDriver getDriver() {
	if(driver==null) {
		driver=new ChromeDriver();
		js=(JavascriptExecutor)driver;
	}
	return driver;
}
public static void killDriver() {
	if(driver!=null) {
		driver.quit();
		js=null;
	}
}
}
