package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementUtils {
    WebDriver driver;
    WaitUtils waitUtils;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
    public void click(By locator) {
        waitUtils.waitForClickability(locator).click();
    }
    public void type(By locator, String text){
       waitUtils.waitForClickability(locator).sendKeys(text);
    }
    public String getText(By locator){
        return waitUtils.waitForVisibility(locator).getText();
    }
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }



}
