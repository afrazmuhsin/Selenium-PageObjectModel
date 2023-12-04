package com.qa.pages;

import com.qa.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    @FindBy(name="username")
    WebElement uname;

    @FindBy(name="password")
    WebElement pwd;

    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form/div[3]/input")
    WebElement signInBtn;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void login(String username , String password){
        uname.sendKeys(username);
        pwd.sendKeys(password);
        signInBtn.click();
    }
}
