package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Driver extends Browser {

    public void implicitWait(Integer time) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public WebElement getElementByClassName(WebElement element, String className) {
        return element.findElement(By.className(className));
    }

    public WebElement getElementByCss(String cssSelector) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            return wait.until(d -> d.findElement(By.cssSelector(cssSelector)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return null;
        }
    }

    public WebElement getElementById(WebElement element, String id) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            return wait.until(d -> d.findElement(By.id(id)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return null;
        }
    }

    public WebElement getElementByLinkText(WebElement element, String linkText) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            return wait.until(d -> element.findElement(By.linkText(linkText)));
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e + ". linkText error: " + linkText);
            return null;
        }
    }

    public Boolean isVisibleByLinkText(String linkText) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(driver -> driver.findElement(By.linkText(linkText)));
            return true;
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e + ". linkText error: " + linkText);
            return false;
        }
    }


    public WebElement getElementByLinkText(String linkText) {
        try {
            return driver.findElement(By.linkText(linkText));
        } catch (Exception e) {
            System.out.println("Cannot get linkText: " + linkText);
            return null;
        }
    }

    public WebElement getElementByName(WebElement element, String name) {
        return element.findElement(By.name(name));
    }

    public WebElement getElementByTagName(WebElement element, String tag) {
        try {
            return element.findElement(By.tagName(tag));
        } catch (Exception e) {
            System.out.println("Cannot get tag name: " + tag);
            return null;
        }
    }

    public WebElement getElementByXpath(WebElement parentElement, String xpath) {
        try {
            return parentElement.findElement(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("Cannot get xpath: " + xpath + " using element");
            return null;
        }
    }

    public Boolean isVisibleByXpath(WebElement parentElement, String xpath) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(driver -> parentElement.findElement(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e + ". Xpath error: " + xpath);
            return false;
        }
    }

    public Boolean isVisibleByXpath(String xpath) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(driver -> driver.findElement(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            System.out.println("Cannot find Element: " + e);
            return false;
        }
    }

    public WebElement getElementByXpath(String xpath) {
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            System.out.println("Cannot get xpath: " + xpath);
            return null;
        }
    }

    public void clickElement(WebElement element) {
        try {
            explicitWaitIsDisplayed(element);
            element.click();
        } catch (Exception e) {
            System.out.println("Cannot found Element: " + element);
        }
    }

    public void clickElementByXpath(WebElement element, String xpath) {
        if (isVisibleByXpath(element, xpath)) {
            WebElement obj = getElementByXpath(element, xpath);
            clickElement(obj);
        }
    }

    public void clickElementByElementInNewTab(WebElement element) {
        if (explicitWaitIsDisplayed(element)) {
            // Create an Actions object
            Actions actions = new Actions(getDriver());

            // Hold down the Ctrl key and click on the element
            actions.keyDown(Keys.CONTROL)
                    .click(element)
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();
        }
    }

    public void clickElementByElementInNewTab2(WebElement element) {
        // Get the target attribute value
        String targetAttributeValue = element.getAttribute("target");

        // If the target attribute is not set or is set to "_blank", click the link
        if (targetAttributeValue == null || "_blank".equals(targetAttributeValue.trim().toLowerCase())) {
            element.click();
        } else {
            // If the target attribute is set to something other than "_blank", handle as needed
            System.out.println("Link is configured to open in a specific target: " + targetAttributeValue);
            // You can choose to handle it differently based on your requirements
        }
    }

    public void switchToNextTab() {
        // Get the current window handle
        String currentWindowHandle = driver.getWindowHandle();

        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();

        // Find the handle of the current tab
        String currentTabHandle = null;
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                currentTabHandle = windowHandle;
                break;
            }
        }

        // Switch to the next tab using JavaScript
        if (currentTabHandle != null) {
            ((JavascriptExecutor) driver).executeScript("window.focus();");
            driver.switchTo().window(currentTabHandle);
        }
    }

    public void switchToFirstTab() {
        // Get the handles of all open windows or tabs
        Set<String> windowHandles = driver.getWindowHandles();

        // Switch to the first window handle
        String firstTabHandle = windowHandles.toArray()[0].toString();
        driver.switchTo().window(firstTabHandle);
    }

    public void closeCurrentTab() {
        getDriver().close();
    }

    public void scrollToPageEnd() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        // Execute JavaScript to scroll to the end of the page
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickElementByXpath(String xpath) {
        if (isVisibleByXpath(xpath)) {
            WebElement obj = getElementByXpath(xpath);
            clickElement(obj);
        }
    }

    public void clickElementByLinkText(String linkText) {
        if (isVisibleByLinkText(linkText)) {
            WebElement obj = getElementByLinkText(linkText);
            clickElement(obj);
        }
    }

    public void enterTextByXpath(String xpath, String content) {
        if (isVisibleByXpath(xpath)) {
            WebElement element = getElementByXpath(xpath);
            element.sendKeys(content);
        }
    }

    public void enterTextByElementAndEnter(WebElement webElement, String content) {
        webElement.sendKeys(content);
        webElement.sendKeys(Keys.ENTER);
    }

    public void clearField(WebElement element) {
        element.clear();
    }

    public Boolean explicitWaitIsDisplayed(WebElement element) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            wait.until(d -> element.isDisplayed());
            return true;
        } catch (Exception e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
    }

    public List<WebElement> loadLiTags(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        List<WebElement> liElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(".//li[not(ancestor::li)]")));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(".//li[not(ancestor::li)]"), liElements.size()));

        return liElements;
    }
}

