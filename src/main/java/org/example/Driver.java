package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Driver {
    WebDriver driver;

    public Driver() {
        System.setProperty("webdriver.chrome.driver", "C:\\LinkedIn_Automation\\LinkedIn\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/knigh/AppData/Local/Google/Chrome/User Data");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    public WebDriver getDrive() {
        return driver;
    }

    public void navigateTo(String link) {
        this.driver.get(link);
    }

    public void implicitWait(Integer time) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public WebElement getElementByClassName(String className) {
        return this.driver.findElement(By.className(className));
    }

    public WebElement getElementByCss(String cssSelector) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(d -> d.findElement(By.cssSelector(cssSelector)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return null;
        }
    }

    public WebElement getElementById(String id) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(d -> d.findElement(By.id(id)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return null;
        }
    }

    public WebElement getElementByLinkName(String linkName) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(d -> d.findElement(By.linkText(linkName)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return null;
        }

    }


    public WebElement getElementByName(String name) {
        return this.driver.findElement(By.name(name));
    }

    public WebElement getElementByTagName(String tag) {
        return this.driver.findElement(By.tagName(tag));
    }

    public WebElement getElementByXpath(String xpath) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(d -> d.findElement(By.xpath(xpath)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return null;
        }
    }

    public void clickElement(WebElement element){
        try{
            explicitWaitIsDisplayed(element);
            element.click();
        }catch(Exception e){
            System.out.println("Cannot found Element: "+element);
        }
    }

    public void sendKeys(WebElement element, String content){
        element.sendKeys(content);
    }

    public void clearField(WebElement element){
        element.clear();
    }

    public void explicitWaitIsDisplayed(WebElement element){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> element.isDisplayed());
    }

    public List<WebElement> loadLiTags(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        List<WebElement> liElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//li[not(ancestor::li)]")));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(".//li[not(ancestor::li)]"), liElements.size()));

        return liElements;
    }

    public void quit(){
        driver.quit();
    }
}

