package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;
import utils.WaitUtils;

public class CartPage {

    WebDriver driver;
    ElementUtils elementUtils;
    WaitUtils waitUtils;

    public CartPage(WebDriver driver){
        this.driver=driver;
        elementUtils = new ElementUtils(driver);
        this.waitUtils = new WaitUtils(driver);
    }


    By cartTitle = By.xpath("//span[normalize-space()='Your Cart']");
    By productName = By.xpath("//div[normalize-space()='Sauce Labs Bike Light']");
    By checkout = By.id("checkout");

    public boolean isCartPageDisplayed(){
        return waitUtils.waitForVisibility(cartTitle).isDisplayed();
    }

    public String getCartProductName(){
        return elementUtils.getText(productName);
//        return driver.findElement(productName).getText();
    }

    public void clickCheckout(){
//        driver.findElement(checkout).click();
        elementUtils.click(checkout);
    }



}
