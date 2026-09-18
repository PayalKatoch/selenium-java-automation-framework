package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;
import utils.WaitUtils;

public class CartPage {

    private final WebDriver driver;
    private final ElementUtils elementUtils;
    WaitUtils waitUtils;

    public CartPage(WebDriver driver){
        this.driver=driver;
        this.elementUtils = new ElementUtils(driver);
        this.waitUtils = new WaitUtils(driver);
    }


    By cartTitle = By.xpath("//span[normalize-space()='Your Cart']");
    By productName = By.xpath("//div[normalize-space()='Sauce Labs Bike Light']");
    By checkout = By.id("checkout");
    By removeButton = By.id("remove-sauce-labs-bike-light");
    By continueShoppingButton = By.id("continue-shopping");

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
    public void removeProduct() {
        elementUtils.click(removeButton);
    }

    public boolean isProductPresent() {
        return elementUtils.isElementPresent(productName);
    }

    public void continueShopping() {
        elementUtils.click(continueShoppingButton);
    }



}
