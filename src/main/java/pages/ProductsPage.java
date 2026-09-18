package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;
import utils.WaitUtils;

public class ProductsPage {

    WebDriver driver;
    WaitUtils waitUtils;
    ElementUtils elementUtils;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.elementUtils = new ElementUtils(driver);
    }

    By productPage = By.className("title");
    By selectProduct = By.xpath("//div[normalize-space()='Sauce Labs Bike Light']");
    By addProduct = By.name("add-to-cart-sauce-labs-bike-light");
    By shoppingCart = By.cssSelector("[data-test='shopping-cart-link']");
    By cartBadgeCount = By.cssSelector(".shopping_cart_badge");

    public boolean isProductsPageDisplayed() {
        return waitUtils.waitForVisibility(productPage).isDisplayed() &&
                waitUtils.waitForVisibility(selectProduct).isDisplayed();
    }

    public void addBikeLightToCart() {
        elementUtils.click(addProduct);
//        waitUtils.waitForClickability(addProduct).click();
    }

    public void clickCart() {
        elementUtils.click(shoppingCart);
    }

    public String getCartCount() {
        return elementUtils.getText(cartBadgeCount);
//    return driver.findElement(cartBadgeCount).getText();
    }

    public void waitForCartCount() {
        waitUtils.waitForText(cartBadgeCount, "1");
    }
}
