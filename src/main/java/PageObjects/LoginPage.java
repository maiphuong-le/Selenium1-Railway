package PageObjects;

import Common.DriverManager;
import Common.ScrollClickHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage {

    // Locators
    private final By txtUsername = By.xpath("//input[@id='username']");
    private final By txtPassword = By.xpath("//input[@id='password']");
    private final By btnLogin = By.xpath("//input[@value='login']");
    private final By lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");

    // Elements
    public WebElement getTxtUsername() {
        return DriverManager.getDriver().findElement(txtUsername);
    }

    public WebElement getTxtPassword() {
        return DriverManager.getDriver().findElement(txtPassword);
    }

    public WebElement getBtnLogin() {
        return DriverManager.getDriver().findElement(btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return DriverManager.getDriver().findElement(lblLoginErrorMsg);
    }

    // Methods
    public HomePage login(String username, String password) {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        
        // Use ScrollClickHandler to avoid click interception
        ScrollClickHandler scrollClickHandler = new ScrollClickHandler(DriverManager.getDriver());
        scrollClickHandler.click(getBtnLogin());
        
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

    public void clickForgotPasswordLink() {
        By forgotPasswordLink = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");
        DriverManager.getDriver().findElement(forgotPasswordLink).click();
    }
}
