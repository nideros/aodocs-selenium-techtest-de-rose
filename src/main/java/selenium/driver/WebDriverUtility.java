package selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.time.Duration;

public class WebDriverUtility {

    public static WebDriver getWebDriver(Browser browser) {
        WebDriver webDriver;
        switch (browser) {
            case FIREFOX:
                //add clearDriverCache() to the setup because of an issue with version 108 of Firefox, as mentioned in the WebDriverManager GitHUb
                WebDriverManager.firefoxdriver().clearDriverCache().setup();
                webDriver = new FirefoxDriver(getFireFoxOptions());
                break;
            case CHROME:
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(getChromeOptions());
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();

        return webDriver;
    }

    public static void closeWebDriver(WebDriver webDriver) {
        if (webDriver != null)
            webDriver.quit();
    }


    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // To start chrome in english
        options.addArguments("lang=en-GB");
        // To start chrome without security warning
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return options;
    }

    private static FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en-GB");
        options.setProfile(profile);
        return options;
    }

}
