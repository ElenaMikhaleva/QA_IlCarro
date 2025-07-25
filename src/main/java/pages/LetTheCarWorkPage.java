package pages;

import dto.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Fuel;

import java.io.File;

public class LetTheCarWorkPage extends BasePage {

    public LetTheCarWorkPage(WebDriver driver) {
        // constructor
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputCity;
    @FindBy(xpath = "//div[@class='pac-container hdpi']//button")
    WebElement btnOkGoogleMaps;
    @FindBy(id = "make")
    WebElement inputManufacturer;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement selectFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(xpath = "//input[@type='file']")
    WebElement inputPhoto;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;

    public void typeAddNewCarForm(Car car) {
        inputCity.sendKeys(car.getCity());
        btnOkGoogleMaps.click();
        inputManufacturer.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
        enterFuel(Fuel.DIESEL.getValue());
        inputSeats.sendKeys(car.getSeats().toString());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys((car.getSerialNumber()));
        inputPrice.sendKeys(car.getPricePerDay() + "");
        // for data provider to work correctly
//        addPhoto(car.getImage());
        btnSubmit.click();
    }

    private void addPhoto(String fileName) {
        inputPhoto.sendKeys(new File("src/main/resources/photos/" + File.separator + fileName).getAbsolutePath());
    }

    private void enterFuel(String fuel) {
        Select select = new Select(selectFuel);
        select.selectByValue(fuel);
    }

    public boolean isBtnSubmitEnabled() {
        return isElementEnabled(btnSubmit);
    }
}
