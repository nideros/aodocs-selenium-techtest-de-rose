package pages.aodocs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AODocsHomePage {

    public static final String baseUrl = "https://www.aodocs.com/";

    WebDriver driver;

    @FindBy(id="ccc-notify-dismiss")
    WebElement closeCookieNotificationButton;

    @FindBy(xpath="//a[@href='" + baseUrl + "contact?request_type=request_demo']")
    WebElement requestDemoButton;

    public AODocsHomePage(WebDriver driver) {
        this.driver = driver;
        if (!new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(baseUrl))) {
            throw new IllegalStateException("This is not the AODocs website Home Page, current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public AODocsRequestDemoPage clickRequestDemoButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(closeCookieNotificationButton)).click();
        requestDemoButton.click();
        return new AODocsRequestDemoPage(driver);
    }

}
