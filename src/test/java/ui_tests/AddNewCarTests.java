package ui_tests;

import dto.Car;
import manager.ApplicationManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.clickHeaderBtn;
import static utils.RandomUtils.generateString;

@Listeners(TestNGListener.class)

public class AddNewCarTests extends ApplicationManager {
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void goToLetTheCarWorkPage() {
        new HomePage(getDriver());
        letTheCarWorkPage = clickHeaderBtn(HeaderMenuItem.LET_THE_CAR_WORK);
    }

    @Test
    public void addNewCar_PositiveTest() {
        Car car = Car.builder()
                .city("Tel Aviv")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2019")
                .fuel("gas")
                .seats(5)
                .carClass("Compact Sedan")
                .serialNumber(generateString(7))
                .pricePerDay(100.77)
                .about("Reliable and fuel-efficient sedan perfect for city driving and small families.")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
    }
}
