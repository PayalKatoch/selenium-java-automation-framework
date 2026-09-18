package tests;

import base.BaseTest;
import config.PropertyReader;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import retry.RetryAnalyzer;

public class SauceDemoTest extends BaseTest {
    @Test(groups = {"smoke", "regression"},retryAnalyzer = RetryAnalyzer.class)
    public void validE2EPurchase() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartpage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        PropertyReader testData = new PropertyReader("testdata.properties");
        loginPage.login(testData.getProperty("username"), testData.getProperty("password"));
        Assert.assertTrue(productsPage.isProductsPageDisplayed());
        productsPage.addBikeLightToCart();
        productsPage.waitForCartCount();
        Assert.assertEquals(productsPage.getCartCount(), "1");
        productsPage.clickCart();

//        Assert.assertTrue(driver.findElement(By.className("title")).isDisplayed(),
//                    "Products page is not displayed");
//
//        Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Bike Light']")).isDisplayed(),"Sauce Labs Bike Light is not displayed");
//        driver.findElement(By.name("add-to-cart-sauce-labs-bike-light")).click();
//        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
//        Thread.sleep(200);
//        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Your Cart']")).isDisplayed(), "Your cart text is not displayed");
//
//        Assert.assertTrue(driver.findElement(By.xpath("//div[normalize-space()='1']")).isDisplayed(),"More then 1 item is displayed");
//        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Bike Light']"));
        //driver.findElement(By.id("checkout")).click();

        Assert.assertTrue(cartpage.isCartPageDisplayed(), "Cart page is not displayed");
        Assert.assertEquals(cartpage.getCartProductName(), "Sauce Labs Bike Light");
        cartpage.clickCheckout();

//        Thread.sleep(500);


//        driver.findElement(By.id("first-name")).sendKeys("payal");
//        driver.findElement(By.id("last-name")).sendKeys("katoch");
//        driver.findElement(By.id("postal-code")).sendKeys("123789");
//        driver.findElement(By.id("continue")).click();


        checkoutPage.enterCustomerInformationAndCheckout("Payal", "Katoc", "12464");

//        Assert.assertTrue(driver.findElement(By.xpath("//span[normalize-space()='Checkout: Overview']")).isDisplayed(), "Checkout Overview page is not displayed");
//            driver.findElement(By.id("finish")).click();
//          driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Bike Light']"));
//          driver.findElement(By.xpath("//div[@class='cart_quantity'][normalize-space()='1']"));
//          driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
//          driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
//          driver.findElement(By.xpath("//div[@class='summary_total_label']"));

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(checkoutOverviewPage.getProductName(), "Sauce Labs Bike Light");
        Assert.assertEquals(checkoutOverviewPage.getQuantity(), "1");
        Assert.assertEquals(checkoutOverviewPage.getItemTotal(), "Item total: $9.99");
        Assert.assertEquals(checkoutOverviewPage.getTax(), "Tax: $0.80");
        Assert.assertEquals(checkoutOverviewPage.getTotal(), "Total: $10.79");
        checkoutOverviewPage.clickFinish();


        WebElement title = driver.findElement(By.xpath("//h2[contains(normalize-space(),'Thank you for your order!')]"));
        Assert.assertTrue(title.isDisplayed(), "Order confirmation message is not displayed");

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        Assert.assertEquals(orderConfirmationPage.getConfirmationMessage(), "Thank you for your order!");
        //   Assert.assertTrue(orderConfirmationPage.isOrderConfirmationDisplayed(), "Order confirmation message is not displayed");


        driver.quit();

    }

}
