package PageObjects;

import Common.DriverManager;
import Model.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class RegisterPage extends GeneralPage {

    // Locators
    private final By txtEmail = By.id("email");
    private final By txtPassword = By.id("password");
    private final By txtConfirmPassword = By.id("confirmPassword");
    private final By txtPID = By.id("pid");
    private final By btnRegister = By.xpath("//input[@value='Register']");
    private final By lblRegisterSuccess = By.xpath("//div[@id='content']");
    private final By lblRegisterFail = By.xpath("//div[@id='content']");
    private final By txtErrorMessage = By.cssSelector("p.message.error");
    private final String fieldErrorLabelPattern =
            "//input[@id='%s']/following-sibling::label[@class='validation-error']";

    // Elements
    public WebElement getTxtEmail() {
        return DriverManager.getDriver().findElement(txtEmail);
    }

    public WebElement getTxtPassword() {
        return DriverManager.getDriver().findElement(txtPassword);
    }

    public WebElement getTxtConfirmPassword() {
        return DriverManager.getDriver().findElement(txtConfirmPassword);
    }

    public WebElement getTxtPID() {
        return DriverManager.getDriver().findElement(txtPID);
    }

    public WebElement getBtnRegister() {
        return DriverManager.getDriver().findElement(btnRegister);
    }

    public WebElement getLblRegisterSuccess() {
        return DriverManager.getDriver().findElement(lblRegisterSuccess);
    }

    public WebElement getLblRegisterFail() {
        return DriverManager.getDriver().findElement(lblRegisterFail);
    }

    // Method
    public void register(String email, String password, String confirmPassword, String pid) {
        getTxtEmail().sendKeys(email);
        getTxtPassword().sendKeys(password);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getTxtPID().sendKeys(pid);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "arguments[0].click();", getBtnRegister());
    }

    public void registerNotConfirm(Account account) {
        getTxtEmail().sendKeys(account.getEmail());
        getTxtPassword().sendKeys(account.getPassword());
        getTxtConfirmPassword().sendKeys(account.getConfirmPassword());
        getTxtPID().sendKeys(account.getPid());
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "arguments[0].click();", getBtnRegister());
    }

    public String getSuccessMessage() {
        return getLblRegisterSuccess().getText();
    }

    public String getErrorMessage() {
        return DriverManager.getDriver().findElement(txtErrorMessage).getText();
    }

    public String getFieldErrorMessage(String fieldId) {
        By fieldErrorLabel = By.xpath(String.format(fieldErrorLabelPattern, fieldId));
        return DriverManager.getDriver().findElement(fieldErrorLabel).getText();
    }

    public void registerConfirm(Account account) {
        getTxtEmail().sendKeys(account.getEmail());
        getTxtPassword().sendKeys(account.getPassword());
        getTxtConfirmPassword().sendKeys(account.getConfirmPassword());
        getTxtPID().sendKeys(account.getPid());
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "arguments[0].click();", getBtnRegister());

        // Optional: verify success
        String successMsg = getSuccessMessage();
        if (!successMsg.contains("registration") && !successMsg.contains("success")) {
            throw new RuntimeException("Account registration failed: " + successMsg);
        }
    }




}
