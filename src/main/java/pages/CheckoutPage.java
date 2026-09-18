package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;

public class CheckoutPage {

    WebDriver driver;
    ElementUtils elementUtils;

    public CheckoutPage(WebDriver driver){
        this.driver=driver;
        this.elementUtils = new ElementUtils(driver);
    }

    By firstName= By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By checkoutButton = By.id("continue");

    public void enterFirstName(String fname){
//        driver.findElement(firstName).sendKeys(fname);
        elementUtils.type(firstName,fname);
    }
    public void enterLastName(String lname){
        elementUtils.type(lastName,lname);
    }
    public void enterPostalCode(String code){
        elementUtils.type(postalCode,code);
    }
    public void clickCheckout(){
        elementUtils.click(checkoutButton);
    }

    public void enterCustomerInformationAndCheckout(String fname,String lname, String code){
        enterFirstName(fname);
        enterLastName(lname);
        enterPostalCode(code);
        clickCheckout();
    }

}
