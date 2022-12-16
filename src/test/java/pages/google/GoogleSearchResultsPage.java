package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.aodocs.AODocsHomePage;
import java.time.Duration;

public class GoogleSearchResultsPage {

    public static final String baseUrl = "https://www.google.com/search?";

    public static final String aodocsBaseUrl = "https://www.aodocs.com/";

    WebDriver driver;

    @FindBy(xpath="//a[@href='" + aodocsBaseUrl + "']")
    WebElement searchResultsContainer;

    public GoogleSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        if (!new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(baseUrl))){
            throw new IllegalStateException("You are not in a Google search results page, current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public AODocsHomePage navigateAODocsWebsite(){
        searchResultsContainer.click();
        return new AODocsHomePage(driver);
    }

}
