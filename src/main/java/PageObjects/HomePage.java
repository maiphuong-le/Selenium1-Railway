package PageObjects;

import Common.DriverManager;
import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    // Locators
    private final By bookTicketTab = By.xpath("//a[@href='/Page/BookTicketPage.cshtml']");
    private final By myTicketTab = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By changePasswordTab = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By logoutTab = By.xpath("//a[@href='/Account/Logout']");
    private final By loginTab = By.xpath("//a[@href='/Account/Login.cshtml']");
    private final By timeTableTab = By.xpath("//a[@href='/Page/TimeTable.cshtml']");
    private final By registerTab = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabChangePassword = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By welcomeMessage = By.cssSelector("div.account > strong");
    private final By tabLogin = By.xpath("//a[@href='/Account/Login.cshtml']");
    private final By tabRegister = By.xpath("//a[@href='/Account/Register.cshtml']");

    protected WebElement getTabChangePassword() {
        return DriverManager.getDriver().findElement(tabChangePassword);
    }

    // Open the home page
    public void open() {
        DriverManager.getDriver().get(Constant.RAILWAY_URL);
    }

    // Click methods
    public void clickBookTicketTab() {
        DriverManager.getDriver().findElement(bookTicketTab).click();
    }

    public void clickMyTicketTab() {
        DriverManager.getDriver().findElement(myTicketTab).click();
    }

    /*public void gotoChangePasswordPage() {
        getTabChangePassword().click();
    }*/

    // Display checks
    public boolean isMyTicketTabDisplayed() {
        return DriverManager.getDriver().findElement(myTicketTab).isDisplayed();
    }

    public boolean isChangePasswordTabDisplayed() {
        return DriverManager.getDriver().findElement(changePasswordTab).isDisplayed();
    }

    public boolean isLogoutTabDisplayed() {
        return DriverManager.getDriver().findElement(logoutTab).isDisplayed();
    }

    public void waitForUrlContains(String partialUrl) {
        new WebDriverWait(DriverManager.getDriver(), java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(partialUrl));
    }

    public LoginPage gotoLoginPage() {
        DriverManager.getDriver().findElement(loginTab).click();
        return new LoginPage();
    }

    public String getWelcomeMessage() {
        return DriverManager.getDriver().findElement(welcomeMessage).getText();
    }

    public void clickRegisterTab() {
        DriverManager.getDriver().findElement(registerTab).click();
    }

    public void clickLoginTab() {
        DriverManager.getDriver().findElement(loginTab).click();
    }


    public void clickTimeTablePage() {
        DriverManager.getDriver().findElement(timeTableTab).click();
    }

    public void gotoChangePasswordPage() {
        getTabChangePassword().click();
        new ChangePasswordPage();
    }

}
