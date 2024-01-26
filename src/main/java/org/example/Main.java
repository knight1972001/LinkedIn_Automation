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
        driver.navigateTo("https://www.linkedin.com");
//        linkedIn.connect_more_people_in_industry();

        linkedIn.connect_people_using_applied_link();

    }


    //    public static void hideMessageBox(Driver driver) {
//        System.out.println("Hide message box");
//        WebElement messaging = driver.getElementByXpath("/html[1]/body[1]/div[5]/div[4]/aside[1]/div[1]");
//        String classMessage = messaging.getAttribute("class");
//        if (!classMessage.contains("minimized")) {
//            WebElement messageBoxButton = driver.getElementByXpath("/html[1]/body[1]/div[5]/div[4]/aside[1]/div[1]/header[1]/div[3]/button[2]");
//            driver.clickElement(messageBoxButton);
//        }
//    }

//    public static void peopleYouMayKnowList(Driver driver) {
//        System.out.println("You may know list");
//
//        List<String> relatePosition = new ArrayList<String>();
//        relatePosition.add("Automation");
//        relatePosition.add("Developer");
//        relatePosition.add("Programmer");
//        relatePosition.add("IT");
//        relatePosition.add("Senior Technical");
//        relatePosition.add("QA Automation");
//        relatePosition.add("Software Developer");
//        relatePosition.add("Full-Stack Developer");
//
//
//        WebElement ulElement = driver.getElementByXpath("//body/div[5]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/aside[1]/section[3]/div[3]/ul[1]");
//        List<WebElement> liElements = ulElement.findElements(By.xpath(".//li[not(ancestor::li)]"));
//
//        for (WebElement li : liElements) {
//            System.out.println("Text of <li>: " + li.getText());
//            if (Utils.isValidString(li.getText(), relatePosition)) {
////                System.out.println("Text of <li>: " + li.getText());
//                System.out.println("VALID");
//            }
////            WebElement connectButton = li.findElement(By.xpath(".//div[1]/button[1]/span[1]"));
////            System.out.println(connectButton.getText() + " button detected.");
//        }
//    }

//    public static void applyJob(Driver driver) throws InterruptedException {
//        System.out.println("Apply Job");
//
//        // Add title you want
//        List<String> titles = new ArrayList<String>();
//        titles.add("QA Automation Engineer");
//        titles.add("Test Automation Engineer");
//        titles.add("QA Engineer");
//        titles.add("Automation Developer");
////        titles.add("");
////        titles.add("");
////        titles.add("");
////        titles.add("");
////        titles.add("");
////        titles.add("");
//
//        WebElement jobButton = driver.getElementByXpath("//span[text()='Jobs']");
//        driver.clickElement(jobButton);
//
//        WebElement jobInput = driver.getElementByXpath("//*[starts-with(@id, 'jobs-search-box-keyword-id-ember')]");
//        jobInput.clear();
//
//        jobInput.sendKeys("QA Automation Engineer");
//        jobInput.sendKeys(Keys.ENTER);
//
//        WebDriverWait wait = new WebDriverWait(driver.getDrive(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".jobs-search-results-list")));
//
//        WebElement listJob = driver.getElementByCss(".jobs-search-results-list");
//
//        WebElement ulElement = listJob.findElement(By.xpath(".//ul"));
//
//        List<WebElement> liElements = ulElement.findElements(By.xpath(".//li[not(ancestor::li)]"));
//
//        for (WebElement li : liElements) {
//            System.out.println("Text of <li>: " + li.getText());
////            if (Utils.isValidString(li.getText(), relatePosition)) {
//////                System.out.println("Text of <li>: " + li.getText());
////                System.out.println("VALID");
////            }
////            WebElement connectButton = li.findElement(By.xpath(".//div[1]/button[1]/span[1]"));
////            System.out.println(connectButton.getText() + " button detected.");
//        }
//    }

//    public static void peopleYouMayKnowExpandList(Driver driver) throws InterruptedException {
//        System.out.println("You may know list");
//
//        List<String> relatePosition = new ArrayList<String>();
//        relatePosition.add("Automation");
//        relatePosition.add("Developer");
//        relatePosition.add("Programmer");
//        relatePosition.add("IT");
//        relatePosition.add("Senior Technical");
//        relatePosition.add("QA Automation");
//        relatePosition.add("Full-stack");
//        relatePosition.add("Full Stack");
//        relatePosition.add("Lead QA");
//        relatePosition.add("Lead Software");
//        relatePosition.add("Lead Developer");
//
//        WebElement showAllButton = driver.getElementByXpath("//body/div[5]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/aside[1]/section[3]/div[3]/div[1]/div[1]/div[1]/a[1]/span[1]");
//        driver.clickElement(showAllButton);
//
//        WebElement fromIndustry = driver.getElementByXpath("//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/button[3]/span[1]");
//        WebElement fromIndustryText = driver.getElementByXpath("//span[text()='From your industry']");
//        driver.clickElement(fromIndustryText);
//
//        WebElement ulElement = driver.getElementByXpath("//body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[4]/div[1]/ul[1]");
//        List<WebElement> liElements = ulElement.findElements(By.xpath(".//li[not(ancestor::li)]"));
//
//        for (WebElement li : liElements) {
////            System.out.println("Text of <li>: " + li.getText());
//            if (Utils.isValidString(li.getText(), relatePosition)) {
//                System.out.println("Text of <li>: " + li.getText());
////                System.out.println("VALID");
//                WebElement connectButton = li.findElement(By.xpath(".//div[1]/button[1]/span[1]"));
////                System.out.println(connectButton.getText() + " button detected.");
//                if (connectButton.getText().equals("Connect")) {
//                    driver.clickElement(connectButton);
////                    System.out.println(connectButton.getText() + " button detected.");
//                }
//            }
////            WebElement connectButton = li.findElement(By.xpath(".//div[1]/button[1]/span[1]"));
////            System.out.println(connectButton.getText() + " button detected.");
//        }
//
//        WebElement closeButton = driver.getElementByXpath("//body[1]/div[3]/div[1]/div[1]/button[1]");
//        driver.clickElement(closeButton);
//    }

}