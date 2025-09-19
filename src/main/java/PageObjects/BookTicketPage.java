package PageObjects;

import Common.DriverManager;
import Common.ScrollClickHandler;
import Model.Ticket;
import DataTypes.SeatType;
import DataTypes.Station;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookTicketPage {

    private final By _cmbDepartDate = By.xpath("//select[@name='Date' or @id='Date' or contains(@class, 'date')]");
    private final By _cmbDepartFrom = By.name("DepartStation");
    private final By _cmbArriveAt = By.name("ArriveStation");
    private final By _cmbSeatType = By.name("SeatType");
    private final By _cmbTicketAmount = By.name("TicketAmount");
    private final By _btnBookTicket = By.xpath("//input[@value='Book ticket']");

    public void selectDepartDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try {
            WebElement dateElement = DriverManager.getDriver().findElement(_cmbDepartDate);
            new Select(dateElement).selectByVisibleText(date.format(formatter));
        } catch (Exception e) {
            // Try alternative selectors for date field
            try {
                WebElement dateElement = DriverManager.getDriver().findElement(By.name("DepartDate"));
                new Select(dateElement).selectByVisibleText(date.format(formatter));
            } catch (Exception e2) {
                // Try with a different selector
                WebElement dateElement = DriverManager.getDriver().findElement(By.xpath("//select[contains(@name, 'Date') or contains(@id, 'Date')]"));
                new Select(dateElement).selectByVisibleText(date.format(formatter));
            }
        }
    }

    public void selectDepartFrom(Station departFrom) {
        new Select(DriverManager.getDriver().findElement(_cmbDepartFrom)).selectByVisibleText(departFrom.getStationName());
    }

    public void selectArriveAt(Station arriveAt) {
        new Select(DriverManager.getDriver().findElement(_cmbArriveAt)).selectByVisibleText(arriveAt.getStationName());
    }

    public void selectSeatType(SeatType seatType) {
        new Select(DriverManager.getDriver().findElement(_cmbSeatType)).selectByVisibleText(seatType.getDescription());
    }

    public void selectTicketAmount(int amount) {
        new Select(DriverManager.getDriver().findElement(_cmbTicketAmount)).selectByVisibleText(String.valueOf(amount));
    }

    public void clickBookTicketButton() {
        WebElement btn = DriverManager.getDriver().findElement(_btnBookTicket);
        ScrollClickHandler scrollClickHandler = new ScrollClickHandler(DriverManager.getDriver());
        scrollClickHandler.click(btn);
    }

    public void bookTicket(Ticket ticket) {
        selectDepartDate(ticket.getDepartDate());
        selectDepartFrom(ticket.getFrom());
        selectArriveAt(ticket.getTo());
        selectSeatType(ticket.getSeatType());
        selectTicketAmount(ticket.getTicketAmount());
        clickBookTicketButton();
    }

    public String BookAndGetId(Ticket ticket) {
        bookTicket(ticket);
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        String ticketId = currentUrl.substring(currentUrl.indexOf("id=") + 3);
        return ticketId;
    }

    public boolean isTicketInformationDisplayed(Ticket ticket) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        String formattedDate = ticket.getDepartDate().format(formatter);
        String xpath = String.format(
                "//table//tr[td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%s'] and td[text()='%d']]",
                ticket.getFrom().getStationName(), ticket.getTo().getStationName(),
                ticket.getSeatType().getDescription(), formattedDate, ticket.getTicketAmount()
        );
        try {
            return DriverManager.getDriver().findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDepartFrom() {
        return new Select(DriverManager.getDriver().findElement(_cmbDepartFrom)).getFirstSelectedOption().getText();
    }

    public String getArriveAt() {
        return new Select(DriverManager.getDriver().findElement(_cmbArriveAt)).getFirstSelectedOption().getText();
    }
}
