package PageObjects;

import Common.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {

    // Locators
    private final By currentPasswordTextbox = By.id("currentPassword");
    private final By newPasswordTextbox = By.id("newPassword");
    private final By confirmPasswordTextbox = By.id("confirmPassword");
    private final By changePasswordButton = By.xpath("//input[@value='Change Password']");
    private final By successMessageLabel = By.xpath("//p[@class='message success']");
    private final By forgotPasswordLink = By.id("forgotPassword");

    // Actions
    public void changePassword(String currentPwd, String newPwd, String confirmPwd) {
        DriverManager.getDriver().findElement(currentPasswordTextbox).sendKeys(currentPwd);
        DriverManager.getDriver().findElement(newPasswordTextbox).sendKeys(newPwd);
        DriverManager.getDriver().findElement(confirmPasswordTextbox).sendKeys(confirmPwd);
        DriverManager.getDriver().findElement(changePasswordButton).click();
    }

    public String getSuccessMessage() {
        WebElement message = DriverManager.getDriver().findElement(successMessageLabel);
        return message.getText();
    }

    public ForgotPasswordPage goToForgotPasswordPage() {
        WebElement forgotPasswordElement = DriverManager.getDriver().findElement(forgotPasswordLink);
        forgotPasswordElement.click();
        return new ForgotPasswordPage();
    }
}
