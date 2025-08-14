package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LanguagePage {
    WebDriver driver;
    public LanguagePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By languageButton = By.xpath("//button[@data-testid='header-language-picker-trigger']");
    private By selectionItems = By.cssSelector("button[data-testid='selection-item']");
    public void openLanguageMenu() throws InterruptedException {
        driver.findElement(languageButton).click();
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