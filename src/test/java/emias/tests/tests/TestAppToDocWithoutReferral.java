package emias.tests.tests;

import emias.tests.model.TestBase;
import org.testng.annotations.Test;

public class TestAppToDocWithoutReferral extends TestBase {

    //Создается запись на прием без направления, затем запись переносится и удаляется

    @Test
        public void testInfomatLogin() throws InterruptedException {
        app.appToDoctorWithoutReferral();
        //app.login(new LoginData("2358810868001039", "23", "39"));
        app.shiftAppointment("WithoutReferral"); // не работало, сделан обход, но надо проверить, почему запись не переносится
        app.cancel();
    }
}
