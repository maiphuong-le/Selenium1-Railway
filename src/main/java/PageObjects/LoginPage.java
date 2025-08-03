package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import Constant.Constant;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends GeneralPage {

    // Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }

    //Methods
    public HomePage login(String username, String password) {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        return new HomePage();
    }

    public void loginWithInvalidAttempts(String username, String wrongPassword, int attempts) {
        for (int i = 1; i <= attempts; i++) {
            clearUsername();
            clearPassword();
            login(username, wrongPassword);
        }
    }



    public String getLoginErrorMessageText() {
        return this.getLblLoginErrorMsg().getText();
    }

    public void clearPassword() {
        this.getTxtPassword().clear();
    }

    public void clearUsername() {
        this.getTxtUsername().clear();
    }
}
