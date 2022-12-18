import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;
import pages.aodocs.AODocsHomePage;
import pages.aodocs.AODocsRequestDemoPage;
import pages.google.GoogleHomePage;
import pages.google.GoogleSearchResultsPage;
import selenium.driver.Browser;
import selenium.driver.WebDriverUtility;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AODocsRequestDemoTest {

    public final static String REQUIRED_FIELD_ERROR_MESSAGE = "Please complete this required field.";
    public final static String EMAIL_FORMAT_ERROR_MESSAGE = "Email must be formatted correctly.";
    public final static String REQUIRED_DROPDOWN_SELECTION_ERROR_MESSAGE = "Please select an option from the dropdown menu.";
    WebDriver driver;

    /***
     *  For the implementation of this code assignment I decided to use the Selenium Page Object Model pattern,
     *  in order to have a more organized JUnit test and overall code.
     *  I had to put the browser inizialization inside the test class instead of @before/beforeAll hook because JUnit
     *  parameterized test does not support parameterized hooks for now.
     */
    @ParameterizedTest
    @EnumSource(Browser.class)
    void test_AODocsRequestDemoErrorMessagesAreCorrectlyDisplayed(Browser browser) {
        driver = WebDriverUtility.getWebDriver(browser);
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);
        GoogleSearchResultsPage googleSearchResultsPage = googleHomePage.searchOnGoogle("aodocs");
        AODocsHomePage aoDocsHomePage = googleSearchResultsPage.navigateAODocsWebsite();
        AODocsRequestDemoPage aoDocsRequestDemoPage = aoDocsHomePage.clickRequestDemoButton();
        aoDocsRequestDemoPage.fillRequestDemoForm();
        assertTrue(aoDocsRequestDemoPage.getLastNameErrorMessage().equalsIgnoreCase(REQUIRED_FIELD_ERROR_MESSAGE));
        assertTrue(aoDocsRequestDemoPage.getEmailErrorMessage().equalsIgnoreCase(EMAIL_FORMAT_ERROR_MESSAGE));
        assertTrue(aoDocsRequestDemoPage.getCompanyNameErrorMessage().equalsIgnoreCase(REQUIRED_FIELD_ERROR_MESSAGE));
        assertTrue(aoDocsRequestDemoPage.getCountryDropdownErrorMessage().equalsIgnoreCase(REQUIRED_DROPDOWN_SELECTION_ERROR_MESSAGE));
        assertTrue(aoDocsRequestDemoPage.getRequestMessageErrorMessage().equalsIgnoreCase(REQUIRED_FIELD_ERROR_MESSAGE));
        assertTrue(aoDocsRequestDemoPage.getHowDidYouHearAboutUsErrorMessage().equalsIgnoreCase(REQUIRED_DROPDOWN_SELECTION_ERROR_MESSAGE));
    }

    @AfterEach
    public void tearDown() {
        WebDriverUtility.closeWebDriver(driver);
    }
}
