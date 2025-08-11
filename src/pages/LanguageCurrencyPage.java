package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LanguageCurrencyPage {

    WebDriver driver;
    
    public LanguageCurrencyPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
     By currencyButton = By.cssSelector("button[data-testid='footer-currency-picker-trigger-desktop']");
    By languageButton = By.cssSelector("button[data-testid='footer-language-picker-trigger-desktop']");
    By selectionItems = By.cssSelector("button[data-testid='selection-item']");

    // Actions
    public void openCurrencyMenu() {
        driver.findElement(currencyButton).click();
    }

    public void selectRandomCurrency() throws InterruptedException {
    	Thread.sleep(1000);
        selectRandomItem();
        Thread.sleep(1000);
        checkTheCurrency();
    }

    public void openLanguageMenu() {
        driver.findElement(languageButton).click();
    }

    public void selectRandomLanguage() {
        selectRandomItem();
    }

    private void selectRandomItem() {
        List<WebElement> items = driver.findElements(selectionItems);
        if (!items.isEmpty()) {
            int index = new Random().nextInt(items.size());
            items.get(index).click();
        }
    }
    public boolean checkTheCurrency() {
    	WebElement theCurrency=driver.findElement(By.cssSelector("button[data-testid='header-currency-picker-trigger'] span[class='ca2ca5203b']"));
    	String theCurrencyText=theCurrency.getText();
    	System.out.println(theCurrencyText);
    	return driver.getPageSource().contains(theCurrencyText);
    }
}