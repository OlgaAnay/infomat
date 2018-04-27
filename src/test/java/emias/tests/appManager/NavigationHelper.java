package emias.tests.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationHelper extends Helper {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }
    protected ApplicationManager manager;

    public void openInfomat() {
        driver.get(manager.props.getProperty("baseURL"));
    }


    public void appToDoctorWithoutReferral() throws InterruptedException {
        //Нажать "Записаться к врачу"
        click(By.xpath("//div[@ng-click=\"controller.makeAppointmentToDoctor()\"]"));
        Thread.sleep(2000);
        //Выбрать первую специальность
        click(By.xpath("//div[@ng-repeat=\"speciality in controller.specialities\"]"));
        Thread.sleep(2000);
        //Выбрать первого врача
        click(By.xpath("//div[@ng-repeat=\"doctor in controller.filteredDoctors track by $index\"]"));
        Thread.sleep(2000);
        //Нажать "Продолжить"
        click_Next();
        Thread.sleep(2000);
        //Выбрать дату и время приема
        selectDayAndTime("createAppointment");
        //Продолжить
        click_Next();
        Thread.sleep(2000);
    }

    private void selectDayAndTime(String app) throws InterruptedException {
        //Выбрать первый день в расписании, у которого !@disabled="disabled"
        selectDay();
        Thread.sleep(2000);
        //Выбрать первое время
        selectTime();
        Thread.sleep(3000);
        //Нажать "Записаться на приём"
        if (app.equals("shiftAppointment")) {
            dbClick(findElement(By.xpath("//div[@ng-click=\"controller.confirmAppointment()\"]")));
        } else {
            dbClick(findElement(By.xpath("//button[@ng-click=\"controller.confirmAppointment()\"]")));
        }
        Thread.sleep(3000);
    }

    private void click_Next() {
        dbClick(findElement(By.xpath("//button[contains(text(),'Продолжить')]")));
    }

    private void selectTime() {
        WebElement time = findElement(By.xpath("//div[@class=\"inf-calendar__item inf-calendar__time inf-btn inf-btn_type_calendar\"]"));
        dbClick(time);
    }

    private void selectDay() throws InterruptedException {
        WebElement day = findElement(By.xpath("//li[@class=\"inf-calendar__item inf-calendar__day inf-btn inf-btn_type_calendar \"]"));
        dbClick(day);
    }

    public void shiftAppointment(String ref) throws InterruptedException {
        //Нажать "Перенести запись"
        dbClick(findElement(By.xpath("//div[@ng-click=\"controller.moveAppointment()\"]")));
        Thread.sleep(3000);
        //Проверка, есть ли запись для переноса
        try {
            assert (isElementPresent(By.xpath("//div[@class=\"inf-group-btn__item inf-btn inf-btn_type_doctor\"]")) == true);
        } catch (Error e) {
            System.out.println("Error assert " + e );
            assert (isTextPresent("Отсутствуют записи для переноса") == true);
        }

        dbClick(findElement(By.xpath("//div[@class=\"inf-group-btn__item inf-btn inf-btn_type_doctor\"]")));
        Thread.sleep(3000);
        if (ref.equals("WithoutReferral")) {
            //Выбрать того же доктора
            dbClick(findElement(By.xpath("//div[@ng-click=\"controller.selectSpecialist(doctor)\"]")));
            click_Next();
        }

        Thread.sleep(3000);
        selectDayAndTime("shiftAppointment");
        //Сейчас тут ошибка, исправить после устранения
        dbClick(findElement(By.xpath("//button[@ng-repeat=\"button in notice.buttons\"]")));
    }

    public void cancel() throws InterruptedException {
        //Выбрать Отменить запись
        dbClick(findElement(By.xpath("//div[@ng-click=\"controller.cancelAppointment()\"]")));
        Thread.sleep(2000);
        //Выбрать запись
        dbClick(findElement(By.xpath("//div[@class=\"inf-group-btn__item inf-btn inf-btn_type_doctor\"]")));
        Thread.sleep(2000);
        //Отменить
        dbClick(findElement(By.xpath("//button[@ng-click=\"controller.cancelAppointment()\"]")));
        Thread.sleep(2000);
        zoom(30);
        assert (isTextPresent("Спасибо, что Вы отменили") == true);
        dbClick(findElement(By.xpath("//div[@ng-click=\"controller.goToState('logout')\"]")));
    }

    public void appByReferral() throws InterruptedException {

        //Нажать "Записаться к врачу"
        click(By.xpath("//div[@ng-click=\"controller.makeAppointmentByReferral()\"]"));
        Thread.sleep(2000);
        //Выбрать первую специальность
        click(By.xpath("//div[@ng-repeat=\"value in controller.referralsList track by $index\"]"));
        Thread.sleep(2000);
        //Выбрать первого врача
//        click(By.xpath("//div[@ng-repeat=\"doctor in controller.filteredDoctors track by $index\"]"));
//        Thread.sleep(2000);
        //Нажать "Продолжить"
        click_Next();
        Thread.sleep(2000);
        //Выбрать дату и время приема
        selectDayAndTime("createAppointment");
        //Продолжить
        click_Next();
        Thread.sleep(2000);
        //Тут ошибка, но запись создается
    }
}
