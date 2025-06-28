package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.HeaderMenuItem;

public abstract class BasePage {
    // родительский класс для всех классов страниц
    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver=wd;
        // потому что должен быть один драйвер в проекте, из ApplicationManager передает сюда
    }

    @FindBy(xpath = "//div[@class='dialog-container']")
    WebElement popUpMessage;

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean validatePopUpMessage(String text) {
        return isTextInElementPresent(popUpMessage, text);
    }

    public void pause(int time) {
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends BasePage> T clickHeaderBtn(HeaderMenuItem headerMenuItem) {
        WebElement element = driver.findElement(By.xpath(headerMenuItem.getLocator()));
        element.click();
        switch (headerMenuItem) {
            case SEARCH -> { return (T) new HomePage(driver); }
            case LET_THE_CAR_WORK -> { return (T) new LetTheCarWorkPage(driver); }
            case SIGN_UP -> { return (T) new SignUpPage(driver); }
            case LOG_IN -> { return (T) new LoginPage(driver); }
            default -> throw new IllegalArgumentException("Invalid parameter headerMenuItem");
        }
    }

    public boolean isElementPresent (WebElement element) {
        return element.isDisplayed();
    }

    public boolean isTextInElementPresent(WebElement element, String text) {
        return element.getText().contains(text);
    }

    public boolean elementIsEnabled(WebElement element) {
        return element.isEnabled()
    }
}
