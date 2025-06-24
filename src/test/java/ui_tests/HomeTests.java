package ui_tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTests extends ApplicationManager {

    @Test
    public void firstTest() {
        System.out.println("hello");
        // проверяет связь @Test и @BeforeMethod

        HomePage homePage = new HomePage(getDriver());
        // browser must open IlCarro and close
    }
}
