package PageObjects;

import Common.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ResetPasswordPage {

    // Locators
    private final By txtNewPassword = By.id("newPassword");
    private final By txtConfirmPassword = By.id("confirmPassword");
    private final By txtPasswordResetToken = By.id("resetToken");
    private final By btnResetPassword = By.xpath("//input[@value='Reset Password']");
    private final By lblErrorMessage = By.xpath("//p[@class='message error']");
    private final By lblErrorMessageConfirmPasswordField = By.xpath("//label[@for='confirmPassword' and @class='validation-error']");
    private final By lblErrorMessageTokenField = By.xpath("//label[@for='resetToken' and @class='validation-error']");

    // WebDriverWait
    private WebDriverWait wait;

    public ResetPasswordPage() {
        this.wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        DriverManager.getDriver().get(url);
    }

    // Elements
    protected WebElement getTxtNewPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtNewPassword));
    }

    protected WebElement getTxtConfirmPassword() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtConfirmPassword));
    }

    protected WebElement getTxtPasswordResetToken() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(txtPasswordResetToken));
    }

    protected WebElement getBtnResetPassword() {
        return wait.until(ExpectedConditions.elementToBeClickable(btnResetPassword));
    }

    protected WebElement getLblErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorMessage));
    }

    protected WebElement getLblErrorMessageConfirmPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorMessageConfirmPasswordField));
    }

    protected WebElement getLblErrorMessageTokenField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblErrorMessageTokenField));
    }

    public void fillResetPasswordForm(String newPassword, String confirmPassword) {
        getTxtNewPassword().sendKeys(newPassword);
        getTxtConfirmPassword().sendKeys(confirmPassword);
    }

    public void clearResetToken() {
        getTxtPasswordResetToken().clear();
    }

    public void clickResetPasswordButton() {
        getBtnResetPassword().click();
    }

    public String getGeneralErrorMessage() {
        return getLblErrorMessage().getText();
    }

    public String getConfirmPasswordErrorMessage() {
        return getLblErrorMessageConfirmPasswordField().getText();
    }

    public String getTokenErrorMessage() {
        return getLblErrorMessageTokenField().getText();
    }
}