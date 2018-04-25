package emias.tests.appManager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver driver;

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openInfomat() {
        driver.get("http://infomat3.emias.dzm.lanit.ru/web-infomat");
    }

}
