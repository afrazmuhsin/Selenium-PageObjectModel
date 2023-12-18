package com.qa.pages;

import com.qa.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends TestBase {

    @FindBy(name="username")
    WebElement uname;

    @FindBy(name="password")
    WebElement pwd;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form/div[3]/input")
    WebElement signInBtn;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/p[2]/a")
    WebElement registerLink;

    public LoginPage(){
        PageFactory.initElements(getDriver(),this);
    }

    @Step("login with username: {0} and password: {1} step...")
    public void login(String username , String password){
        uname.sendKeys(username);
        pwd.sendKeys(password);
        signInBtn.click();
    }
}
