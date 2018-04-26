package emias.tests.appManager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Helper {
    public static WebDriver driver;
    public boolean acceptNextAlert = true;
    protected ApplicationManager manager;

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            System.out.println("I see " + by + " ");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("I don't see " + by + " ");
            return false;
        }
    }

    public boolean isTextPresent(String expected) throws InterruptedException {
        try {
            if (driver.findElement(By.cssSelector("body")).getText()
                    .contains(expected)) {
                System.out.println("'" + expected + "'" + " -  text is on this page");
                return true;
            } else {
                System.out.println("'" + expected + "'" + " -  text is NOT on this page");
                return false;
            }
        } catch (WebDriverException e) {
            throw new WebDriverException(e.getMessage());
        }
    }

    public int countElements(By locator) {
        return findElements(locator).size();
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void dbClick(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).doubleClick().perform();
        } catch (Exception e) {
            System.out.println("Element " + element + " was not clickable "
                    + e.getStackTrace());
        }
    }

    public void type(By locator, String text) {
        if (text != null || text != "") {
            WebElement element = findElement(locator);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(By locator) {
        findElement(locator).click();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

    public String closeAlertAndGetItsText() {
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

    public void zoom(int zoom) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.body.style.zoom = '"+zoom+"%';");
    }

    public void wait_actions_present() {
        WebDriverWait wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Выберите действие')]")));
    }

}
