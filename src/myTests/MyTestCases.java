package myTests;

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
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(url);
        driver.manage().window().maximize();
        home=new HomePage(driver);
    }

    @Test(priority=1)
    public void checkTheHomePage() throws InterruptedException {
    	Thread.sleep(1000);
    	home.checkHomePageTest();
    }
    @Test(priority=2)
    public void fillData() throws InterruptedException {
    	country=TestDataGenerator.getCountryRandomly();
    	home.fillData(country);
    }

//    @AfterTest
//    public void tearDown() {
//        DriverFactory.killDriver();
//    }
}
