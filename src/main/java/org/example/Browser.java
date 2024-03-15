package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
    WebDriver driver;

    public Browser() {
//        System.setProperty("webdriver.chrome.driver", "C:\\LinkedIn_Automation\\LinkedIn\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/knigh/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    public void navigateTo(String link) {
        this.driver.get(link);
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void quit(){
        this.driver.quit();
    }

}



