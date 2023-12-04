package com.qa.tests;

import com.qa.TestBase;
import com.qa.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoginTest extends TestBase {
    LoginPage loginPage;

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
        driver.quit();
    }
}
