package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementUtils;

public class LoginPage {
    private final WebDriver driver;
    private final ElementUtils elementUtils;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }

    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    By loginError = By.cssSelector(".error-message-container.error");

    public void enterUsername(String user) {
        elementUtils.type(username, user);
    }

    public void enterPassword(String pass) {
        elementUtils.type(password, pass);
    }

    public void clickLogin() {
        elementUtils.click(loginButton);
    }
    public String getLoginErrorMessage() {
        return elementUtils.getText(loginError);
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }


}
