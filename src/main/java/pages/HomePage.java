package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.PropertiesReader;

import java.time.LocalDate;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        setDriver(driver);
//        driver.get("https://ilcarro.web.app/search");
        driver.get(PropertiesReader.getProperty("login.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnHeaderLogin;
    // = driver.findElement(By.xpath(...))

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDate;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    // ---------- calendar ---------------
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnMonthYear;

    public void clickBtnHeaderLogin() {
        btnHeaderLogin.click();
    }

    public void typeSearchForm(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDate.sendKeys(dateToString(startDate) + " - " + dateToString(endDate));
        removeAttrDisabledBtnYalla();
        btnYalla.click();
    }

    private String dateToString(LocalDate date) {
        return date.getMonthValue() + "/" + date.getDayOfMonth() + "/" + date.getYear();
    }

    public void typeSearchFormCalendar(String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDate.click();
        typeCalendar(startDate);
        typeCalendar(endDate);
        removeAttrDisabledBtnYalla();
        btnYalla.click();
    }

    private void typeCalendar(LocalDate date) {
        btnMonthYear.click();
        String year = Integer.toString(date.getYear());
        driver.findElement(By.xpath("//div[contains(text(),'" + year + "')]")).click();
        String month = date.getMonth().toString();
        driver.findElement(By.xpath("//div[contains(text(),'" + month.substring(0,3) + "')]")).click();
        String day = String.valueOf(date.getDayOfMonth());
        driver.findElement(By.xpath("//div[contains(text(),'" + day + "')]")).click();

    }

}
