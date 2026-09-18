package tests;
import base.BaseTest;
import data.CheckoutDataProvider;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import config.PropertyReader;

public class CheckoutValidationTest extends BaseTest {

    PropertyReader testData = new PropertyReader("testdata.properties");

    @Test(groups = "regression", dataProvider = "checkoutValidationData", dataProviderClass = CheckoutDataProvider.class)

    public void mandatoryCheckoutValidation(
            String firstName,
            String lastName,
            String postalCode,
            String expectedError) {

        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.login(
                testData.getProperty("username"),
                testData.getProperty("password"));

        productsPage.addBikeLightToCart();
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.enterCustomerInformation(
                firstName,
                lastName,
                postalCode);

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                expectedError);
    }

    @Test(groups = "regression")
    public void verifyOrderPriceCalculation() {

        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        loginPage.login(
                testData.getProperty("username"),
                testData.getProperty("password")
        );

        productsPage.addBikeLightToCart();
        productsPage.clickCart();

        cartPage.clickCheckout();

        checkoutPage.enterCustomerInformation(
                testData.getProperty("firstName"),
                testData.getProperty("lastName"),
                testData.getProperty("postalCode"));

        double itemTotal = overviewPage.getItemTotalValue();

        double tax = overviewPage.getTaxValue();

        double actualTotal = overviewPage.getTotalValue();

        double expectedTotal = itemTotal + tax;

        Assert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Item total + tax should equal final total");
    }
}