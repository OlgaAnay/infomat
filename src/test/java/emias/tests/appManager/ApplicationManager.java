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
    private NavigationHelper navigationHelper ;
    private Helper helper;
    public String browser = "chrome";
    public StringBuffer verificationErrors = new StringBuffer();


    public void stop() {
        driver.quit();
    }

    public void init() {
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
    }

    public void openLoginForm() {
        WebElement infomat1 = helper.findElement(By.xpath("//div[@ng-dblclick=\"onOmsHack()\"]"));
        String text2 = infomat1.getText();
        System.out.println("text2  = " + text2);
        helper.dbClick(infomat1);
    }

    public void login(LoginData loginData) throws InterruptedException {
        helper.click(By.xpath("//input[contains(@ng-model,'controller.oms')]"));
        helper.type(By.xpath("//input[contains(@ng-model,'controller.oms')]"), loginData.getOms());
        helper.click(By.xpath("//input[contains(@ng-model,'controller.oms1')]"));
        helper.type(By.xpath("//input[contains(@ng-model,'controller.oms1')]"), loginData.getOms1());
        helper.click(By.xpath("//input[contains(@ng-model,'controller.oms2')]"));
        helper.type(By.xpath("//input[contains(@ng-model,'controller.oms2')]"), loginData.getOms2());

        helper.click(By.xpath("//button[contains(text(),'Продолжить')]"));
        Thread.sleep(500);
    }

    public void start() throws InterruptedException {
        navigationHelper.openInfomat();
        Thread.sleep(700);
        openLoginForm();
        Thread.sleep(700);
        login(new LoginData("2358810868001039", "23", "39"));
    }

    public Helper getHelper() {
        return helper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
