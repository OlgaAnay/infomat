package emias.tests.appManager;

import emias.tests.model.LoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SessionHelper extends Helper{

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void openLoginForm() throws InterruptedException {
        Thread.sleep(700);
        WebElement infomat1 = findElement(By.xpath("//div[@ng-dblclick=\"onOmsHack()\"]"));
        Thread.sleep(700);
        String text2 = infomat1.getText();
        System.out.println("text2  = " + text2);
        dbClick(infomat1);
    }

    public void login(LoginData loginData) throws InterruptedException {
        click(By.xpath("//input[contains(@ng-model,'controller.oms')]"));
        type(By.xpath("//input[contains(@ng-model,'controller.oms')]"), loginData.getOms());
        click(By.xpath("//input[contains(@ng-model,'controller.oms1')]"));
        type(By.xpath("//input[contains(@ng-model,'controller.oms1')]"), loginData.getOms1());
        click(By.xpath("//input[contains(@ng-model,'controller.oms2')]"));
        type(By.xpath("//input[contains(@ng-model,'controller.oms2')]"), loginData.getOms2());
        click(By.xpath("//button[contains(text(),'Продолжить')]"));
        Thread.sleep(500);
    }
}
