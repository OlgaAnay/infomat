package emias.tests.appManager;

import emias.tests.model.LoginData;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
    public static final String CONFIG_FILE = "application.properties";
    public static WebDriver driver;
    public static Properties props = new Properties();
    public StringBuffer verificationErrors = new StringBuffer();
    public String browser;
    public String verificationErrorString = verificationErrors.toString();

    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private Helper helper;

    public void init() throws InterruptedException {
        try {
            props.load(new FileInputStream(CONFIG_FILE));
            System.out.println(props.toString());
            browser = props.getProperty("browser");
        } catch (IOException e) {
            Assert.fail("Cannot read configuration properties file "
                    + new File(CONFIG_FILE).getAbsolutePath());
        }

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

    public void stop() {
        driver.quit();
    }

    public void openLoginFormAndLogin() throws InterruptedException {
        sessionHelper.openLoginForm();
        sessionHelper.login(new LoginData(props.getProperty("oms"), props.getProperty("oms1"), props.getProperty("oms2")));
        sessionHelper.isOnMainPage();
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

    public void shiftAppointment(String ref) throws InterruptedException {
        navigationHelper.shiftAppointment(ref);
    }

    public void cancel() throws InterruptedException {
        navigationHelper.cancel();
    }

    public void appByReferral() throws InterruptedException {
        navigationHelper.appByReferral();
    }
}
