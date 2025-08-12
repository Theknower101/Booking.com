package myTests;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.BookingForm;
import pages.HomePage;
import pages.HotelsPage;
import pages.LanguageCurrencyPage;
import pages.LoginPage;
import utils.DriverFactory;
import utils.TestDataGenerator;

public class MyTestCases {
    WebDriver driver;
    HomePage home;
    LoginPage login;
    HotelsPage hotels;
    BookingForm booking;
    LanguageCurrencyPage lang;
    String url="https://www.booking.com/";
    String country;
    Random rand=new Random();
    @BeforeTest
    public void setUp() throws TimeoutException, InterruptedException {
        driver = DriverFactory.getDriver();
        driver.get(url);
        driver.manage().window().maximize();
        home=new HomePage(driver);
        home.closeSignUpPopupIfExists();
        lang=new LanguageCurrencyPage(driver);
        login=new LoginPage(driver);
        hotels=new HotelsPage(driver);
        booking=new BookingForm(driver);
    }
    @Test(priority=1)
    public void checkTheHomePage() throws InterruptedException {
    	Thread.sleep(1000);
    	home.checkHomePageTest();
    }
    @Test(priority=2)
    public void searchForHotels() throws InterruptedException, TimeoutException {
    	Thread.sleep(1000);
    	country=TestDataGenerator.getCountryRandomly();
    	home.fillData(country);
    }
  
    @Test(priority=3)
    public void testLanguageSelection() throws InterruptedException {
    	Thread.sleep(1000);
    	lang.openLanguageMenu();
    	lang.selectRandomLanguage();
    }
    @Test(priority = 4)
    public void testCurrencySelection() throws InterruptedException {
    	Thread.sleep(1000);
    	lang.openCurrencyMenu();
    	Thread.sleep(1000);
    	lang.selectRandomCurrency();
    }
    @Test(priority = 5)
    public void openHotelAndBook() throws InterruptedException {
       hotels.reserveHotel();
    }
    @Test(priority = 6)
    public void fillBookingForm() throws InterruptedException {
       booking.fillBookingForm();
    }
    @Test(priority=7)
    public void loginProcess() throws InterruptedException {
    	login.login();
    }

    @AfterTest
    public void tearDown() {
        DriverFactory.killDriver();
    }
}
