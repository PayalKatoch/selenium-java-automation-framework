package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;

public class OrderConfirmationPage {

    WebDriver driver;
    ElementUtils elementUtils;

    public OrderConfirmationPage(WebDriver driver){
        this.driver=driver;
        this.elementUtils = new ElementUtils(driver);
    }

    By confirmationMessage = By.xpath("//h2[contains(normalize-space(),'Thank you for your order!')]");

    public String getConfirmationMessage(){
        return elementUtils.getText(confirmationMessage);

    }
}
