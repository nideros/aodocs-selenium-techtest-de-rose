package pages.aodocs;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AODocsRequestDemoPage {

    public static final String requestDemoUrl = "https://www.aodocs.com/contact?request_type=request_demo";

    WebDriver driver;

    @FindBy(name="firstname")
    WebElement firstNameTextField;

    @FindBy(name="email")
    WebElement emailTextField;

    @FindBy(name="company_size__c")
    WebElement companySizeDropdown;

    @FindBy(xpath="//label[contains(@for,'LEGAL_CONSENT')]")
    WebElement legalConsentCheckBox;

    @FindBy(xpath="//input[@type='submit']")
    WebElement submitButton;

    public AODocsRequestDemoPage(WebDriver driver) {
        this.driver = driver;
        if (!new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains(requestDemoUrl))) {
            throw new IllegalStateException("This is not the AODocs Page in which you can request a demo, current page is: " + driver.getCurrentUrl());
        }
        PageFactory.initElements(driver, this);
    }

    public WebElement getErrorMessageWebElement(String classContains){
        return driver.findElement(By.xpath("//div[contains(@class,'" + classContains + "')]//label[@class='hs-error-msg']"));
    }

    public String getLastNameErrorMessage(){
        return getErrorMessageWebElement("hs_lastname").getText();
    }

    public String getEmailErrorMessage(){
        return getErrorMessageWebElement("hs_email").getText();
    }

    public String getCompanyNameErrorMessage(){
        return getErrorMessageWebElement("hs_company hs-company").getText();
    }

    public String getCountryDropdownErrorMessage(){
        return getErrorMessageWebElement("hs_country").getText();
    }

    public String getRequestMessageErrorMessage(){
        return getErrorMessageWebElement("hs_your_request__c").getText();
    }

    public String getHowDidYouHearAboutUsErrorMessage(){
        return getErrorMessageWebElement("hs_how_did_you_hear_about_us_").getText();
    }

    public AODocsRequestDemoPage fillRequestDemoForm(){
        firstNameTextField.sendKeys("Nicola");
        emailTextField.sendKeys(RandomStringUtils.randomAlphabetic(12));
        Select companySizeSelector = new Select(companySizeDropdown);
        companySizeSelector.selectByIndex(1);
        new Actions(driver).moveToElement(legalConsentCheckBox).click();
        submitButton.click();
        return this;
    }

}
