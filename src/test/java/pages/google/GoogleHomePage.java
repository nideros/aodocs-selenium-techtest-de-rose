package pages.google;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {

    public static final String baseUrl = "https://www.google.com/";

    WebDriver driver;

    @FindBy(xpath="//input[@name='q']")
    WebElement searchTextField;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(baseUrl);

        //manage Google "Before you continue" iframe
        this.driver.manage().deleteCookieNamed ("CONSENT");
        this.driver.manage().addCookie(new Cookie("CONSENT","YES+"));
        this.driver.navigate().refresh();

        PageFactory.initElements(driver, this);
    }

    public GoogleSearchResultsPage searchOnGoogle(String searchTerm){
        searchTextField.sendKeys(searchTerm);
        searchTextField.sendKeys(Keys.ENTER);
        return new GoogleSearchResultsPage(driver);
    }
}
