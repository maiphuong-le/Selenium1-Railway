package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private final By registerTab = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By tabChangePassword = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By welcomeMessage = By.cssSelector("div.account > strong");

    protected WebElement getTabChangePassword() {

        return Constant.WEBDRIVER.findElement(tabChangePassword);
    }

    // Open the home page
    public void open() {
        Constant.WEBDRIVER.get(Constant.RAILWAY_URL);
    }

    // Click methods
    public void clickBookTicketTab() {
        Constant.WEBDRIVER.findElement(bookTicketTab).click();
    }

    public void clickMyTicketTab() {
        Constant.WEBDRIVER.findElement(myTicketTab).click();
    }

    public void gotoChangePasswordPage() {
        getTabChangePassword().click();

    }


    // Display checks
    public boolean isMyTicketTabDisplayed() {
        return Constant.WEBDRIVER.findElement(myTicketTab).isDisplayed();
    }

    public boolean isChangePasswordTabDisplayed() {
        return Constant.WEBDRIVER.findElement(changePasswordTab).isDisplayed();
    }

    public boolean isLogoutTabDisplayed() {
        return Constant.WEBDRIVER.findElement(logoutTab).isDisplayed();
    }

    public void waitForUrlContains(String partialUrl) {
        new WebDriverWait(Constant.WEBDRIVER, java.time.Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(partialUrl));
    }

    public PageObjects.LoginPage gotoLoginPage() {
        Constant.WEBDRIVER.findElement(loginTab).click();
        return new PageObjects.LoginPage();
    }

    public String getWelcomeMessage() {
        return Constant.WEBDRIVER.findElement(welcomeMessage).getText();
    }

    public void clickRegisterTab() {
        Constant.WEBDRIVER.findElement(registerTab);
    }

}