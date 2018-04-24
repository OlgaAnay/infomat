package emias.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class InfomatLogin {

        protected static WebDriver driver;
        public String browser = "chrome";
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();

        @BeforeMethod()
        public void setUp() throws Exception {

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
        }

        @Test
        public void testInfomatLogin() throws InterruptedException {
            openInfomat();
            Thread.sleep(700);
            openLoginForm();
            Thread.sleep(700);
            login("2358810868001039", "23", "39");
        }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            System.out.println("I see "+by+"");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("I don't see "+by+"");
            return false;
        }
    }
    public boolean isTextPresent(String expected) throws InterruptedException {

        try {
            if (driver.findElement(By.cssSelector("body")).getText()
                    .contains(expected)) {
                System.out.println(expected + " -  text is on this page");
                return true;
            } else {
                System.out.println(expected + " -  text is NOT on this page");
                return false;

            }
        } catch (WebDriverException e) {
            throw new WebDriverException(e.getMessage());
        }
    }
    protected int countElements(By locator) {
        return findElements(locator).size();
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    private void openLoginForm() {
        WebElement infomat1 = findElement(By.xpath("//div[@ng-dblclick=\"onOmsHack()\"]"));
        String text2 = infomat1.getText();
        System.out.println("text2  = " + text2);
        dbClick(infomat1);
    }

    private void dbClick(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).doubleClick().perform();
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable "
                    + e.getStackTrace());
        }
    }



    private void login(String oms, String oms1, String oms2) throws InterruptedException {
        click(By.xpath("//input[contains(@ng-model,'controller.oms')]"));
        type(By.xpath("//input[contains(@ng-model,'controller.oms')]"), oms);
        click(By.xpath("//input[contains(@ng-model,'controller.oms1')]"));
        type(By.xpath("//input[contains(@ng-model,'controller.oms1')]"), oms1);
        click(By.xpath("//input[contains(@ng-model,'controller.oms2')]"));
        type(By.xpath("//input[contains(@ng-model,'controller.oms2')]"), oms2);

        click(By.xpath("//button[contains(text(),'Продолжить')]"));
        Thread.sleep(500);
    }

    private void openInfomat() {
        driver.get("http://infomat3.emias.dzm.lanit.ru/web-infomat");
    }

    protected void type(By locator, String text) {
        if (text != null || text != "") {
            WebElement element = findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }
    protected void click(By locator) {
        findElement(locator).click();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    @AfterMethod()
        public void tearDown() throws Exception {
            driver.quit();
        }



        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }
