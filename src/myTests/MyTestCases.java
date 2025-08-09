package myTests;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.DriverFactory;
import utils.TestDataGenerator;

public class MyTestCases {
    WebDriver driver;
    HomePage home;
    String url="https://www.booking.com/";
    String country;
    @BeforeTest
    public void setUp() throws TimeoutException, InterruptedException {
        driver = DriverFactory.getDriver();
        driver.get(url);
        driver.manage().window().maximize();
        home=new HomePage(driver);
        home.closeSignUpPopupIfExists();
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
    @AfterTest
    public void tearDown() {
        DriverFactory.killDriver();
    }
}
