package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import static utils.RandomUtils.generateEmail;

@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnHeaderLogin();
        loginPage = new LoginPage(getDriver());
    }

//    old variant
//    @Test
//    public void loginPositiveTest() {
//        User user = new User("elenam@gmail.com", "Password$1");
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnHeaderLogin();
//        LoginPage loginPage = new LoginPage(getDriver());
//        loginPage.typeLoginForm(user);
//    }

    @Test
    public void loginPositiveTest() {
        UserLombok user = UserLombok.builder()
                .username("elenam@gmail.com")
                .password("Password$1")
                .build();
        logger.info("test data: " + user.toString());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validatePopUpMessage("Logged in success"), "loginPositiveTest_lombok");
    }

    @Test
    public void loginNegativeTest_emptyUsername() {
        UserLombok user = UserLombok.builder()
                .username("")
                .password("Password$1")
                .build();
        logger.info("test data: " + user.toString());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validateMessageErrorUsername("Email is required"), "loginNegativeTest_emptyUsername");
    }

    @Test
    public void loginNegativeTest_invalidUsername() {
        UserLombok user = UserLombok.builder()
                .username("as")
                .password("Password$1")
                .build();
        logger.info("test data: " + user.toString());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validateMessageErrorUsername("It'snot look like email"), "loginNegativeTest_emptyUsername");
    }

    @Test
    public void loginNegativeTest_unregUser() {
        UserLombok user = UserLombok.builder()
                .username(generateEmail(5))
                .password("Password$1")
                .build();
        logger.info("test data: " + user.toString());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeTest_unregUser");
    }

    @Test
    public void loginNegativeTest_emptyPassword() {
        UserLombok user = UserLombok.builder()
                .username("elenam@gmail.com")
                .password("")
                .build();
        logger.info("test data: " + user.toString());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validateMessageErrorPassword("Password is required"), "loginNegativeTest_emptyPassword");
    }

    @Test
    public void loginNegativeTest_invalidPassword() {
        UserLombok user = UserLombok.builder()
                .username("elenam@gmail.com")
                .password("Password$")
                .build();
        logger.info("test data: " + user.toString());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeTest_invalidPassword");
    }
}
