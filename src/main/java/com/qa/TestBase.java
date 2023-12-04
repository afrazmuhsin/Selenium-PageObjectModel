package com.qa;

import org.config.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static ConfigFileReader configFileReader = new ConfigFileReader();
    public static WebDriver driver;
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;
    public static void initialization() throws MalformedURLException {
        String browserName = configFileReader.getBrowserName();

        if (configFileReader.getRemoteValue().equals("true")) {
            if (browserName.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath() + "/chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                //options.addArguments("--headless");
                chromeOptions.addArguments("--ignore-ssl-errors=yes");
                chromeOptions.addArguments("--ignore-certificate-errors");
                driver = new RemoteWebDriver(new URL(configFileReader.getSeleniumHubAddress()), chromeOptions);
            } else if (browserName.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", configFileReader.getDriverPath() + "/geckodriver");
                FirefoxOptions fireFoxOptions = new FirefoxOptions();
                //options.addArguments("--headless");
                fireFoxOptions.addArguments("--ignore-ssl-errors=yes");
                fireFoxOptions.addArguments("--ignore-certificate-errors");
                driver = new RemoteWebDriver(new URL(configFileReader.getSeleniumHubAddress()), fireFoxOptions);
            }
        } else {
            if (browserName.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/Users/MuhsinKamal/Documents/MuhsinProjects/Selenium-POM/drivers" + "/chromedriver");
                driver = new ChromeDriver();
            } else if (browserName.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "/Users/MuhsinKamal/Documents/MuhsinProjects/Selenium-POM/drivers" + "/geckodriver");
                driver = new FirefoxDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(configFileReader.getApplicationUrl());
    }
}
