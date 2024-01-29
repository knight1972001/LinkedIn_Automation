package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Driver driver = new Driver();
        LinkedIn linkedIn = new LinkedIn(driver);
        Indeed indeed = new Indeed(driver);
        driver.navigateTo("https://www.linkedin.com");
//        linkedIn.connect_more_people_in_industry();

//        linkedIn.connect_people_using_applied_link();
        indeed.connect_people_using_applied_link();
    }
}