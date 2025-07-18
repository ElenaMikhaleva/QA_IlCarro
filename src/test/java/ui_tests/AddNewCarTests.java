package ui_tests;

import data_provider.CarDP;
import dto.Car;
import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.clickHeaderBtn;
import static utils.PropertiesReader.getProperty;
import static utils.RandomUtils.generateString;

@Listeners(TestNGListener.class)

public class AddNewCarTests extends ApplicationManager {
    LetTheCarWorkPage letTheCarWorkPage;
    LoginPage loginPage;

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
                .image("download.jpg")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
    }

    @Test(dataProvider = "addNewCarDP", dataProviderClass = CarDP.class)
    public void addNewCarDP_PositiveTest(Car car) {
        letTheCarWorkPage.typeAddNewCarForm(car);
    }

    @Test(dataProvider = "addNewCarDPFile", dataProviderClass = CarDP.class)
    public void addNewCar_NegativeTest_emptyManufacturer(Car car) {
        logger.info("test data --> " + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        Assert.assertFalse(letTheCarWorkPage.isBtnSubmitEnabled());
    }

    @Test
    public void addNewCar_NegativeTest_invalidSeats() {
        Car car = Car.builder()
                .city("Tel Aviv")
                .manufacture("Toyota")
                .model("Corolla")
                .year("2019")
                .fuel("gas")
                .seats(0)
                .carClass("Compact Sedan")
                .serialNumber(generateString(7))
                .pricePerDay(100.77)
                .about("Reliable and fuel-efficient sedan perfect for city driving and small families.")
                .image("")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
    }
}
