package myTests;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.BookingForm;
import pages.CurrencyPage;
import pages.HomePage;
import pages.HotelsPage;
import pages.LanguagePage;
import pages.OfferPage;
import pages.SignUpPage;
import pages.SupportPage;
import utils.DriverFactory;
import utils.TestDataGenerator;

public class MyTestCases {
    WebDriver driver;
    HomePage home;
    SupportPage support;
    SignUpPage sign;
    HotelsPage hotels;
    BookingForm booking;
    OfferPage offer;
    LanguagePage lang;
    CurrencyPage currency;
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
        lang=new LanguagePage(driver);
        sign=new SignUpPage(driver);
        hotels=new HotelsPage(driver);
        booking=new BookingForm(driver);
        support=new SupportPage(driver);
        offer=new OfferPage(driver);
        currency=new CurrencyPage(driver);
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
    }
    @Test(priority = 4)
    public void testCurrencySelection() throws InterruptedException {
    	currency.openCurrencyMenu();
    	Thread.sleep(1000);
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
    public void signUpProcess() throws InterruptedException {
    	sign.signUp();
    }
    @Test(priority=8)
    public void testSupportPage() {
    	support.enterToSupport();
    }
    @Test(priority=9)
    public void checkOfferPage() throws InterruptedException {
    	offer.enterToOfferPage();
    }

    @AfterTest
    public void tearDown() {
        DriverFactory.killDriver();
    }
}
