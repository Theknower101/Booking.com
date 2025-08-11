package myTests;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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
    }
    @Test(priority=1)
    public void checkTheHomePage() throws InterruptedException {
    	Thread.sleep(1000);
    	home.checkHomePageTest();
    }
    @Test(priority=2)
    public void searchForHotels() throws InterruptedException, TimeoutException {
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
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        driver.findElement(By.name("firstname")).sendKeys("Linda");
        Thread.sleep(500);
        driver.findElement(By.name("lastname")).sendKeys("alelayyan");
        Thread.sleep(500);
        driver.findElement(By.name("email")).sendKeys("test123@gmail.com");
        Thread.sleep(500);
        driver.findElement(By.name("address1")).sendKeys("Amman");
        Thread.sleep(500);
        driver.findElement(By.name("city")).sendKeys("Amman");
        Thread.sleep(500);
        driver.findElement(By.name("zip")).sendKeys("1234");
        Thread.sleep(500);
       Select mySelect=new Select(driver.findElement(By.className("ed4d3c8194")));
       mySelect.selectByIndex(rand.nextInt(1,10));
        Thread.sleep(500);
        driver.findElement(By.name("phoneNumber")).sendKeys("079999999");

        driver.findElement(By.cssSelector("button[type='submit']")).click();
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
