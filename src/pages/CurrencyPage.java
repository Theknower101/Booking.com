package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CurrencyPage {
WebDriver driver;
public CurrencyPage(WebDriver theDriver) {
	this.driver=theDriver;
}
By currencyButtonLocator=By.xpath("//button[@data-testid='header-currency-picker-trigger']");
By selectionItems = By.cssSelector("button[data-testid='selection-item']");
public void openCurrencyMenu() throws InterruptedException {
    driver.findElement(currencyButtonLocator).click();
    Thread.sleep(1000);
    selectRandomItem();
}
private void selectRandomItem() {
    List<WebElement> items = driver.findElements(selectionItems);
    if (!items.isEmpty()) {
        int index = new Random().nextInt(items.size());
        items.get(index).click();
    }
}
}
