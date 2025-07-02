package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;
import pages.HomePage;
import pages.ResultsPage;

import java.time.LocalDate;

public class SearchTests extends ApplicationManager {

    @Test(retryAnalyzer = RetryAnalyzerCount.class)
    public void searchPositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchForm("Tel Aviv", LocalDate.of(25,7,10), LocalDate.of(25, 8, 10));
        boolean result = new ResultsPage(getDriver()).validateUrl("results");
        Assert.assertTrue(result);
    }

    @Test
    public void searchCaldendar_PositiveTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchFormCalendar("Tel Aviv", LocalDate.of(25,7,10), LocalDate.of(25, 8, 10));
        boolean result = new ResultsPage(getDriver()).validateUrl("results");
        Assert.assertTrue(result);
    }

    @Test(expectedExceptions = org.openqa.selenium.NoSuchElementException.class)
    public void searchCaldendar_NegativeTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.typeSearchFormCalendar("Tel Aviv", LocalDate.of(25,5,10), LocalDate.of(25, 8, 10));
        boolean result = new ResultsPage(getDriver()).validateUrl("results");
    }
}
