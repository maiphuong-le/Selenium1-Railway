package PageObjects;

import Common.DriverManager;
import org.openqa.selenium.By;

public class ForgotPasswordPage extends GeneralPage {

    private final By txtEmail = By.id("email");
    private final By btnSendInstructions = By.xpath("//input[@value='Send Instructions']");

    public void requestPasswordReset(String email) {
        DriverManager.getDriver().findElement(txtEmail).sendKeys(email);
        DriverManager.getDriver().findElement(btnSendInstructions).click();
    }
}
