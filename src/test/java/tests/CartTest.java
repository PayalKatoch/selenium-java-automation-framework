package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import config.PropertyReader;

public class CartTest extends BaseTest {

    PropertyReader testData =
            new PropertyReader("testdata.properties");

    @Test(groups = {"smoke", "regression"})
    public void addProductToCart() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login(
                testData.getProperty("username"),
                testData.getProperty("password")
        );

        productsPage.addBikeLightToCart();

        Assert.assertEquals(
                productsPage.getCartCount(),
                "1"
        );

        productsPage.clickCart();

        Assert.assertEquals(
                cartPage.getCartProductName(),
                "Sauce Labs Bike Light"
        );
    }


    @Test(groups = "regression")
    public void removeProductFromCart() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login(
                testData.getProperty("username"),
                testData.getProperty("password")
        );

        productsPage.addBikeLightToCart();
        productsPage.clickCart();

        cartPage.removeProduct();

        Assert.assertFalse(
                cartPage.isProductPresent(),
                "Product should not be present after removal"
        );
    }


    @Test(groups = "regression")
    public void cartPersistsAfterNavigation() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login(
                testData.getProperty("username"),
                testData.getProperty("password")
        );

        productsPage.addBikeLightToCart();
        productsPage.clickCart();

        Assert.assertEquals(
                cartPage.getCartProductName(),
                "Sauce Labs Bike Light"
        );

        cartPage.continueShopping();

        Assert.assertEquals(
                productsPage.getCartCount(),
                "1"
        );

        productsPage.clickCart();

        Assert.assertEquals(
                cartPage.getCartProductName(),
                "Sauce Labs Bike Light"
        );
    }
}