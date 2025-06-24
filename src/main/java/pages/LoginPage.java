package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage (WebDriver driver) {
        // constructor
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail; // = driver.findElement(By.id("email");

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    @FindBy(css = "label[for='email']~div>div")
    WebElement messageErrorUsername;

//    @FindBy(xpath =  "//div[text()=' Email is required ']")
//    WebElement messageErrorUsername;

    @FindBy(css = "label[for='password']~div")
    WebElement messageErrorPassword;

    public void typeLoginForm(UserLombok user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnYalla.click();
    }

    public boolean validateMessageErrorUsername(String text) {
        return isTextInElementPresent(messageErrorUsername, text);
    }

    public boolean validateMessageErrorPassword(String text) {
        return isTextInElementPresent(messageErrorPassword, text);
    }

    public void print() {
        System.out.println(messageErrorUsername.getText());
    }
}
