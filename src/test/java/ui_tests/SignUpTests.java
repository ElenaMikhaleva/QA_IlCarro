package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.*;
import static utils.RandomUtils.generateEmail;

@Listeners(TestNGListener.class)

public class SignUpTests extends ApplicationManager {

    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void goToSignUpPage() {
        homePage = new HomePage(getDriver());
        signUpPage = clickHeaderBtn(HeaderMenuItem.SIGN_UP);
    }

    @Test
    public void signUpPositiveTest() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("Took")
                .username(generateEmail(5))
                .password("Password$1")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.validatePopUpMessage("Registered"));
    }

    @Test
    public void signUpNegativeTest_emptyName() {
        UserLombok user = UserLombok.builder()
                .firstName("")
                .lastName("Took")
                .username(generateEmail(5))
                .password("Password$1")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isErrorMessagePresent("Name is required"));
    }

    @Test
    public void signUpNegativeTest_emptyLastName() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("")
                .username(generateEmail(5))
                .password("Password$1")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isErrorMessagePresent("Last name is required"));
    }

    @Test
    public void signUpNegativeTest_emptyEmail() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("Took")
                .username("")
                .password("Password$1")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isErrorMessagePresent("Email is required"));
    }
    @Test
    public void signUpNegativeTest_invalidEmail() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("Took")
                .username("secondbreakfast")
                .password("Password$1")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isErrorMessagePresent("Wrong email format"), "signUpNegativeTest_invalidEmail");
    }

    @Test
    public void signUpNegativeTest_emptyPassword() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("Took")
                .username(generateEmail(5))
                .password("")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isErrorMessagePresent("Password is required"));
    }
    @Test
    public void signUpNegativeTest_invalidPassword() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("Took")
                .username(generateEmail(5))
                .password("Password!")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickCheckBox();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isErrorMessagePresent("Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }

    @Test
    public void signUpNegativeTest_WOCheckbox() {
        UserLombok user = UserLombok.builder()
                .firstName("Peregrin")
                .lastName("Took")
                .username(generateEmail(5))
                .password("Password$1")
                .build();
        signUpPage.typeSignUpForm(user);
        signUpPage.clickBtnYalla();
        Assert.assertFalse(signUpPage.isBtnYallaEnabled());
    }
}
