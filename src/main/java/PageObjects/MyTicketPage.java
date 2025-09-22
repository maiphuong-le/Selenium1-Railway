package PageObjects;

import Common.DriverManager;
import Common.ScrollClickHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyTicketPage {

    private ScrollClickHandler scrollClickHandler;

    private final By _lblHeader = By.xpath("//div[@id='content']/h1");

    public MyTicketPage() {
        scrollClickHandler = new ScrollClickHandler(DriverManager.getDriver());
    }

    public String getHeader() {
        return DriverManager.getDriver().findElement(_lblHeader).getText();
    }

    public void cancelTicketById(String ticketId) {
        String xpath = String.format("//input[@type='button' and @value='Cancel' and contains(@onclick, '%s')]", ticketId);
        WebElement cancelButton = DriverManager.getDriver().findElement(By.xpath(xpath));
        scrollClickHandler.click(cancelButton);

        WebDriverWait alertWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(3));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.accept();
    }

    public boolean isTicketIdExists(String ticketId) {
        String xpath = String.format("//input[@type='button' and @value='Cancel' and contains(@onclick, '%s')]", ticketId);
        try {
            WebElement ticketElement = DriverManager.getDriver().findElement(By.xpath(xpath));
            return ticketElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
