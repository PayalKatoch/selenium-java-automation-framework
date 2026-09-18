package tests;

import base.BaseTest;
import data.LoginDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import retry.RetryAnalyzer;

public class LoginErrorTest extends BaseTest {
    @Test(groups = "regression", dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class,retryAnalyzer = RetryAnalyzer.class)
    public void invalidLogin(String username, String password, String expectedError) {

        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        String actualError = loginPage.getLoginErrorMessage();
        Assert.assertEquals(actualError, expectedError);
    }
}
