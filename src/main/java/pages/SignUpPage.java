package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class SignUpPage extends BasePage {

    public SignUpPage (WebDriver driver) {
        // constructor
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "input#name")
    WebElement inputName;

    @FindBy(css = "input#lastName")
    WebElement inputLastName;

    @FindBy(css = "input#email")
    WebElement inputEmail;

    @FindBy(css = "input#password")
    WebElement inputPassword;

    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> messagesErrorList;

    public boolean isErrorMessagePresent(String text) {
        for (WebElement e : messagesErrorList) {
           if (e.getText().contains(text)) return true;
        }
        return false;
    }

    public void typeSignUpForm(UserLombok user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckBox() {
        int width = checkBox.getRect().getWidth();
        int height = checkBox.getRect().getHeight();
        System.out.println("checkbox: " + width + " X " + height);
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBox, -(width/20*9), 0).click().perform();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public boolean isBtnYallaEnabled() {
        return isElementEnabled(btnYalla);
    }
}
