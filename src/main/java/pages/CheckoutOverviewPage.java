package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;

public class CheckoutOverviewPage {

    WebDriver driver;
    ElementUtils elementUtils;
    public CheckoutOverviewPage(WebDriver driver){
        this.driver=driver;
        this.elementUtils = new ElementUtils(driver);
    }

    By productName= By.xpath("//div[normalize-space()='Sauce Labs Bike Light']");
    By productQuantity = By.xpath("//div[@class='cart_quantity'][normalize-space()='1']");
    By itemPrice = By.xpath("//div[@class='summary_subtotal_label']");
    By tax = By.xpath("//div[@class='summary_tax_label']");
    By totalPrice= By.xpath("//div[@class='summary_total_label']");
    By finish = By.id("finish");

    public String getProductName(){
        return elementUtils.getText(productName);
    }
    public String getQuantity(){
        return elementUtils.getText(productQuantity);
    }
    public String getItemTotal(){
        return elementUtils.getText(itemPrice);
    }
    public String getTax(){
       return elementUtils.getText(tax);
    }
    public String getTotal(){
        return elementUtils.getText(totalPrice);
    }
    public void clickFinish(){
        elementUtils.click(finish);
    }


}
