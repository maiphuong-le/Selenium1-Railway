package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import javax.swing.*;

public class RegisterPage extends GeneralPage {

    // Locators
    private final By txtEmail = By.id("email");
    private final By txtPassword = By.id("password");
    private final By txtConfirmPassword = By.id("confirmPassword");
    private final By txtPID = By.id("pid");
    private final By btnRegister = By.xpath("//input[@value='Register']");
    private final By lblRegisterSuccess = By.xpath("//div[@id='content']");
    private final By lblRegisterFail = By.xpath("//div[@id='content']");

    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(txtEmail);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }

    public WebElement getTxtPID() {
        return Constant.WEBDRIVER.findElement(txtPID);
    }

    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegister);
    }

    public WebElement getLblRegisterSuccess() {
        return Constant.WEBDRIVER.findElement(lblRegisterSuccess);
    }

    public WebElement getLblRegisterFail() {
        return Constant.WEBDRIVER.findElement(lblRegisterFail);
    }

    // Method
    public void register(String email, String password, String confirmPassword, String pid) {
        getTxtEmail().sendKeys(email);
        getTxtPassword().sendKeys(password);
        getTxtConfirmPassword().sendKeys(confirmPassword);
        getTxtPID().sendKeys(pid);
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", getBtnRegister());

    }

    public String getSuccessMessage() {
        return getLblRegisterSuccess().getText();
    }

    public String getErrorMessage() {
        return Constant.WEBDRIVER.findElement(By.cssSelector("p.message.error")).getText();
    }

    public String getFieldErrorMessage(String fieldId) {
        return Constant.WEBDRIVER.findElement(By.xpath("//input[@id='" + fieldId + "']/following-sibling::label[@class='validation-error']")).getText();
    }



}
