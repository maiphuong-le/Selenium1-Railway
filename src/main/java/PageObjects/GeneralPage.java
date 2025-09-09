package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Common.DriverManager;

public class GeneralPage {

    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

    // Elements
    protected WebElement getTabLogin() {
        return DriverManager.getDriver().findElement(tabLogin);
    }

    protected WebElement getTabLogout() {
        return DriverManager.getDriver().findElement(tabLogout);
    }

    protected WebElement getLblWelcomeMessage() {
        return DriverManager.getDriver().findElement(lblWelcomeMessage);
    }

    // Methods
    public String getWelcomeMessage() {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }


}
