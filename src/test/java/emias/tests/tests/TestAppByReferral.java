package emias.tests.tests;

import emias.tests.model.TestBase;
import org.testng.annotations.Test;

public class TestAppByReferral extends TestBase {

    //Создается запись на прием по направлению, затем запись переносится и удаляется

    @Test
        public void testInfomatLogin() throws InterruptedException {
 //       app.appByReferral();
        //app.login(new LoginData("2358810868001039", "23", "39"));
        app.shiftAppointment("WithReferral"); // не работало, сделан обход, но надо проверить, почему запись не переносится
        app.cancel();
    }
}
