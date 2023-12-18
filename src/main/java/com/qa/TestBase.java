package com.qa;

import org.config.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static ConfigFileReader configFileReader = new ConfigFileReader();

    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver driver;
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;

    protected static String browserName = configFileReader.getBrowserName();
    protected static void initialization() throws MalformedURLException {

        if (configFileReader.getRemoteValue().equals("true")) {
            getBrowserDriver(configFileReader.getSeleniumHubAddress());
        } else if((configFileReader.getRemoteValue().equals("false")) && (configFileReader.getDockerValue().equals("false"))) {
            if (browserName.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers" + "/chromedriver");
                driver = new ChromeDriver();
            } else if (browserName.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "/Users/MuhsinKamal/Documents/MuhsinProjects/Selenium-POM/drivers" + "/geckodriver");
                driver = new FirefoxDriver();
            }
        } else {
            getBrowserDriver(configFileReader.getLocalSeleniumHubAddress());
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(configFileReader.getApplicationUrl());
    }

    protected static void getBrowserDriver(String seleniumHubAddress) throws MalformedURLException {
        if (browserName.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--ignore-ssl-errors=yes");
            chromeOptions.addArguments("--ignore-certificate-errors");
            driver = new RemoteWebDriver(new URL(seleniumHubAddress), chromeOptions);
        } else if (browserName.equals("firefox")) {
            FirefoxOptions fireFoxOptions = new FirefoxOptions();
            fireFoxOptions.addArguments("--ignore-ssl-errors=yes");
            fireFoxOptions.addArguments("--ignore-certificate-errors");
            driver = new RemoteWebDriver(new URL(seleniumHubAddress), fireFoxOptions);
        }
    }
}
