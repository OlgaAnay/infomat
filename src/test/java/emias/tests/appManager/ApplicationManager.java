package emias.tests.appManager;

import emias.tests.model.LoginData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ApplicationManager {
    public static WebDriver driver;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private Helper helper;
    public String browser = "chrome";
    public StringBuffer verificationErrors = new StringBuffer();


    public void stop() {
        driver.quit();
    }

    public void init() throws InterruptedException {
        if ("firefox".equals(browser)) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.manager.showWhenStarting",
                    false);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                    "text/csv");
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                    "*.xlsx");
            System.setProperty("webdriver.gecko.driver", ".\\geckodriver\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("moz:firefoxOptions", options);
            driver = new FirefoxDriver(capabilities);
        }  else if ("chrome".equals(browser)) {
            System.setProperty("webdriver.chrome.driver",
                    ".\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        helper = new Helper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        start();
    }

    public void openLoginFormAndLogin() throws InterruptedException {
        sessionHelper.openLoginForm();
        sessionHelper.login(new LoginData("2358810868001039", "23", "39"));
        navigationHelper.isOnMainPage();
    }

    public void start() throws InterruptedException {
        navigationHelper.openInfomat();
        Thread.sleep(700);
        openLoginFormAndLogin();
    }

    public Helper getHelper() {
        return helper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public void appToDoctorWithoutReferral() throws InterruptedException {
        navigationHelper.appToDoctorWithoutReferral();
    }

    public void shiftAppointment() throws InterruptedException {
        navigationHelper.shiftAppointment();
    }

    public void cancel() throws InterruptedException {
        navigationHelper.cancel();
    }
}
