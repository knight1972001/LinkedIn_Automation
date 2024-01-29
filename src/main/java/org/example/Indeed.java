package org.example;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Indeed extends LinkedIn {

    public Indeed(Driver driver) {
        super(driver);
    }

    public void connect_people_using_applied_link() {
        // get all the link
        List<String> links = Utils.getAllIndeedLink();

        int count = 1;
        for (String link : links) {
            System.out.println("Link " + count);
            navigate_to_job_posting(link);
            count++;
        }
    }

    public void navigate_to_job_posting(String link){
        try{
            driver.navigateTo(link);
            // Waiting to load site
            Thread.sleep(5000);

            WebElement h2Element = driver.getElementByXpath("//h2[@data-testid='jobsearch-JobInfoHeader-title']");
            // Locate the outermost span element within the h2
            WebElement span = driver.getElementByXpath(h2Element, ".//span[1]/span");

            String jobTitleText = Utils.subtractStrings(h2Element.getText(), span.getText());

            WebElement divCompanyName = driver.getElementByXpath("//div[@data-testid='inlineHeader-companyName']");
            String companyNameText = divCompanyName.getText();

            driver.navigateTo("https://www.linkedin.com/");
            // Waiting to load site
            Thread.sleep(5000);

            // search for searchbox
            WebElement searchBox = driver.getElementByXpath("//input[@class='search-global-typeahead__input']");
            driver.enterTextByElementAndEnter(searchBox, companyNameText);

            // Waiting to load site
            Thread.sleep(3000);

            WebElement companyTag = driver.getElementByXpath("//div[@class='search-nec__hero-kcard-v2']");
            driver.clickElement(companyTag);

            super.onTheCompanySite(jobTitleText, companyNameText);
        }catch(Exception e){
            System.out.println("Error from navigate_to_job_posting(): " + e.getMessage());
        }
    }

    public void quickPrint(WebElement web){
        System.out.println(web.getText());
    }
}
