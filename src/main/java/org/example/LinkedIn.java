package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LinkedIn {

    Driver driver;

    List<String> relatePosition = new ArrayList<String>();
    List<String> hiringPosition = new ArrayList<String>();


    public LinkedIn(Driver webDriver) {
        driver = webDriver;
        // relate position
        relatePosition.add("Automation");
        relatePosition.add("Developer");
        relatePosition.add("Programmer");
        relatePosition.add("IT");
        relatePosition.add("Senior Technical");
        relatePosition.add("QA Automation");
        relatePosition.add("Software Developer");
        relatePosition.add("Full-Stack Developer");
        relatePosition.add("Software Engineer");

        hiringPosition.add("Senior Software");
        hiringPosition.add("Senior Developer");
        hiringPosition.add("Senior Technical");
        hiringPosition.add("Tech Lead");
    }

    public Driver getDriver() {
        return this.driver;
    }

    public WebElement getNav() {
        if (driver.isVisibleByXpath("//header[@id='global-nav']")) {
            return driver.getElementByXpath("//header[@id='global-nav']");
        } else {
            System.out.println("There is an error when trying to get Navbar.");
            return null;
        }

    }

    public WebElement getPageContent() {
        return this.driver.getElementByXpath("//body[1]");
    }

    public void connect_more_people_in_industry() throws InterruptedException {
        goToMyPage();
        connectPeopleInYourIndustry();
    }

    public void goToMyPage() {
        WebElement navBar = getNav();
        if (navBar != null) {
            driver.clickElementByXpath(navBar, "//span[normalize-space()='Me']");
            driver.clickElementByLinkText("View Profile");
        }
    }

    public void connectPeopleInYourIndustry() throws InterruptedException {


        if (driver.isVisibleByXpath("//body/div[@class='application-outlet']/div[@class='authentication-outlet']/div[@id='profile-content']/div[@class='body']/div[2]/div[1]/div[1]/aside/section[.//span[text()='People you may know']]")) {
            // get People you may know list
            WebElement peopleYouMayKnow = driver.getElementByXpath("//body/div[@class='application-outlet']/div[@class='authentication-outlet']/div[@id='profile-content']/div[@class='body']/div[2]/div[1]/div[1]/aside/section[.//span[text()='People you may know']]");

            // click show all
            driver.clickElementByXpath(peopleYouMayKnow, ".//span[text()='Show all']");

            // Waiting for the popup loaded
            Thread.sleep(5000);

            // Get popup
            if (driver.isVisibleByXpath("//div[@role='dialog']")) {
                WebElement popUp = driver.getElementByXpath("//div[@role='dialog']");
                // get text from navbar popup
                if (driver.isVisibleByXpath(popUp, ".//div[2]/div[1]/div[1]")) {
                    WebElement navBarPopUp = driver.getElementByXpath(popUp, ".//div[2]/div[1]/div[1]");

                    //Check if there is navbar popup
                    if (navBarPopUp.getText().contains("From your industry")) {
                        // CLick to section "From your industry"
                        driver.clickElementByXpath(navBarPopUp, ".//span[text()='From your industry']");

                        //get the list only
                        WebElement list = driver.getElementByXpath(popUp, ".//div[2]/div[1]/div[4]");

                        //get ulElement
                        WebElement ulElement = driver.getElementByXpath(list, ".//div/ul");

                        // List of Web Element <li> tags
                        List<WebElement> liElements = ulElement.findElements(By.xpath(".//li[not(ancestor::li)]"));

                        int count = 0;

                        for (WebElement li : liElements) {
                            // System.out.println("Text of <li>: " + li.getText());
                            if (Utils.isValidString(li.getText(), relatePosition)) {
                                // System.out.println("Text of <li>: " + li.getText());
                                // System.out.println("VALID");
                                WebElement connectButton = li.findElement(By.xpath(".//div[1]/button[1]/span[1]"));
                                if (connectButton.getText().equals("Connect")) {
                                    System.out.println(connectButton.getText() + " button detected.");
                                    driver.clickElement(connectButton);
                                    count++;
                                }
                            }
                        }

                        if (count == 0) {
                            System.out.println("No more new connection. comeback for later.");
                        } else {
                            System.out.println(count + " connection request sent.");
                        }
                    } else {
                        // No navbar popup found
                        System.out.print("NO Navbar popup found, checking existing list...");

                        //get the list only
                        WebElement list = driver.getElementByXpath(popUp, ".//div[2]/div[1]/div[4]");

                        //get ulElement
                        WebElement ulElement = driver.getElementByXpath(list, ".//div/ul");

                        // List of Web Element <li> tags
                        List<WebElement> liElements = ulElement.findElements(By.xpath(".//li[not(ancestor::li)]"));

                        int count = 0;

                        for (WebElement li : liElements) {
                            // System.out.println("Text of <li>: " + li.getText());
                            if (Utils.isValidString(li.getText(), relatePosition)) {
                                // System.out.println("Text of <li>: " + li.getText());
                                // System.out.println("VALID");
                                WebElement connectButton = li.findElement(By.xpath(".//div[1]/button[1]/span[1]"));
                                if (connectButton.getText().equals("Connect")) {
                                    System.out.println(connectButton.getText() + " button detected.");
                                    driver.clickElement(connectButton);
                                    count++;
                                }
                            }
                        }
                        if (count == 0) {
                            System.out.println("No more new connection. comeback for later.");
                        } else {
                            System.out.println(count + " connection request sent.");
                        }
                    }
                }
            }
        }
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Original border style of the element
            String originalBorderStyle = element.getCssValue("border");

            // Apply a border style to highlight the element
            js.executeScript("arguments[0].style.border='2px solid red'", element);

            // Wait for a short time to see the highlighted element (you can adjust this)
            Thread.sleep(10000);

            // Reset the border style to the original
            js.executeScript("arguments[0].style.border='" + originalBorderStyle + "'", element);

        } catch (Exception e) {
            System.out.println("Error highlighting element: " + e.getMessage());
        }
    }

    public void connect_people_using_applied_link() {
        // get all the link
        List<String> links = Utils.getAllTheAppliedLink();

        int count = 1;
        for (String link : links) {
            System.out.println("Link " + count);
            connectCompanyMember(link);
            count++;
        }
    }

    public void connectCompanyMember(String link) {
        try {
            driver.navigateTo(link);

            // Waiting to load site
            Thread.sleep(8000);

            // get content
            WebElement main = driver.getElementByXpath("//main[@id='main']");

            // get job title
            WebElement jobTitle = driver.getElementByXpath("//span[@class='job-details-jobs-unified-top-card__job-title-link']");
//            System.out.println(jobTitle.getText());
            String jobTitleText = jobTitle.getText();

            if (driver.isVisibleByXpath(main, "//h2[text()='Meet the hiring team']")) {
                System.out.println("Detected hiring team");

                // Get member box and click
                WebElement memberBox = driver.getElementByXpath("//div[@class='hirer-card__container']");
                WebElement hiringTeam = driver.getElementByXpath(memberBox, ".//div[2]/a");
                // Extract hiring team name
                String name = hiringTeam.getText().split(" ")[0];

                // Extract company name
                WebElement jobSide = driver.getElementByXpath(main, ".//div[1]/div[2]");
                WebElement secondDiv = driver.getElementByXpath(jobSide, "//div[@class='job-details-jobs-unified-top-card__primary-description-container']");
                WebElement companyName = driver.getElementByXpath(secondDiv, ".//div/a");
                String companyNameText = companyName.getText();

                String note = "Dear " + name + ";\n" +
                        "I'm writing to express my interest in the " + jobTitleText + " position at " + companyNameText + ". I've already applied through LinkedIn and would like to bring my application here to your attention. If you want to discuss this further, don't hesitate to contact me\n" +
                        "Thank you\n" +
                        "Long";

                connectToProfile(hiringTeam, note, name);
            } else {
                System.out.println("Not Detected hiring team");
                // get job side (ignore menu job)
                WebElement jobSide = driver.getElementByXpath(main, ".//div[1]/div[2]");

                // detect company Name
                WebElement secondDiv = driver.getElementByXpath(jobSide, "//div[@class='job-details-jobs-unified-top-card__primary-description-container']");
                // click to navigate to company
                WebElement companyName = driver.getElementByXpath(secondDiv, ".//div/a");
                String companyNameText = companyName.getText();
                driver.clickElementByXpath(secondDiv, ".//div/a");

                // Waiting to page loaded
                Thread.sleep(5000);

                WebElement companyNavigation = driver.getElementByXpath("//nav[@aria-label='Organization’s page navigation']");
                // click to people tab
                driver.clickElementByXpath(companyNavigation, ".//a[text()='People']");

                // Waiting to page loaded
                Thread.sleep(5000);

//                WebElement associatedMemberBox = driver.getElementByXpath("//div[@class='artdeco-card org-people__card-margin-bottom']");
//                // expand box
//                driver.clickElementByXpath(associatedMemberBox, ".//button[text()='Show more']");

                // Load members
                WebElement associatedMemberBox = driver.getElementByXpath("//div[@class='artdeco-card org-people__card-margin-bottom']");
                WebElement numberOfMember = driver.getElementByXpath(associatedMemberBox, ".//h2[@class='text-heading-xlarge']");

                int allMember = Utils.extractNumber(numberOfMember.getText());
                int scrollTime = 0;
                int count = 0;

                // each load views show 12 members only.
                if (allMember > 120) {
                    scrollTime = 10;
                } else {
                    scrollTime = allMember / 12;
                }

                while (count <= scrollTime) {
                    driver.scrollToPageEnd();
                    Thread.sleep(3000);
                    count++;
                }
                System.out.println("All members load");

                // get all members
                WebElement content = driver.getElementByXpath("//div[@class='org-grid__content-height-enforcer']");
                WebElement ulMemberList = driver.getElementByXpath(content, ".//div[1]/div[2]/div[1]/div[1]/ul[1]");

                List<WebElement> liElements = ulMemberList.findElements(By.xpath(".//li[not(ancestor::li)]"));
                // check each member
                int requestSentCount = 0; // limit send to 5 members each company.
                for (WebElement li : liElements) {
                    if (Utils.isValidString(li.getText(), hiringPosition)) {
//                        System.out.println("Text of <li>: " + li.getText());
//                        System.out.println("VALID");
                        if (driver.isVisibleByXpath(li, ".//div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/a[1]/div[1]") && requestSentCount <=5) {
                            WebElement nameCard = driver.getElementByXpath(li, ".//div[1]/section[1]/div[1]/div[1]/div[2]/div[1]/a[1]/div[1]");
//                        System.out.println(nameCard.getText().split(" ")[0]);
                            String name = nameCard.getText().split(" ")[0];
                            String note = "Dear " + name + ";\n" +
                                    "I'm writing to express my interest in the " + jobTitleText + " position at " + companyNameText + ". I've already applied through LinkedIn and would like to bring my application here to your attention. If you want to discuss this further, don't hesitate to contact me\n" +
                                    "Thank you\n" +
                                    "Long";

                            // getting Exactly Member profile Link
                            WebElement memberProfile = driver.getElementByXpath(li, ".//div[1]/section[1]/div[1]/div[1]/div[2]/div[1]");

                            connectToProfile(memberProfile, note, name);
                            requestSentCount++;
                        } else {
                            System.out.println("Cannot click to member page due to privacy from LinkedIn");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error from connectCompanyMember(): " + e.getMessage());
        }
    }

    public void connectToProfile(WebElement profile, String note, String name) {
        driver.clickElementByElementInNewTab(profile);
        driver.switchToNextTab();

        // Perform on the second tab.
        // Waiting to new page loading
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Detect Connect button
        WebElement main = driver.getElementByXpath("//main[@class='scaffold-layout__main']");
        WebElement buttonMenu = driver.getElementByXpath(main, ".//section[1]/div[2]/div[3]/div[1]");

        // check if already connected
        // click More Button
        driver.clickElementByXpath(buttonMenu, ".//span[text()='More']");
        // scan text
        WebElement dropdownMenu = driver.getElementByXpath(buttonMenu, ".//div[.//span[text()='More']]");
        if ((buttonMenu.getText().contains("Connect") && !buttonMenu.getText().contains("Connection")) || (dropdownMenu.getText().contains("Connect") && !dropdownMenu.getText().contains("Connection"))) {
            if (buttonMenu.getText().contains("Connect")) {
                // if button Menu is show outside dropdown\
                driver.clickElementByXpath(buttonMenu, ".//span[text()='Connect']");
            } else {
                // if button Menu is show inside dropdown
                driver.clickElementByXpath(buttonMenu, ".//div[1]/div[2]");

                // click on Connect button
                driver.clickElementByXpath(dropdownMenu, ".//span[text()='Connect']");
            }
            driver.clickElementByXpath("//span[normalize-space()='Add a note']");
            driver.enterTextByXpath("//textarea[@id='custom-message']", note);

            // get dialog to find Submit button
            WebElement dialog = driver.getElementByXpath("//div[@role='dialog']");

            driver.clickElementByXpath(dialog, ".//div[3]/button[2]");
            System.out.println(driver.getElementByXpath(dialog, ".//div[3]/button[2]").getText() + " to " + name);

            // Waiting to send message
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Already sent connect request to this person");
        }

        // Close and navigate back to first tab (member list).
        driver.closeCurrentTab();
        driver.switchToFirstTab();
    }

    public void quickPrint(WebElement e) {
        System.out.println(e.getText());
    }

}
