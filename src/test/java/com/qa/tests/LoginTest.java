package com.qa.tests;

import com.qa.TestBase;
import com.qa.listeners.TestAllureListener;
import com.qa.pages.LoginPage;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Listeners({TestAllureListener.class, AllureTestNg.class})
public class LoginTest extends TestBase {
    LoginPage loginPage;
    WebDriver driver;

    public LoginTest(){
        super();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void loginTest(){
        loginPage.login("test@gmail.com","pwd123");
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
    }
}
