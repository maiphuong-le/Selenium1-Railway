package Model;

import DataTypes.SeatType;
import DataTypes.Station;

import java.time.LocalDate;

public class Ticket {
    private LocalDate departDate;
    private Station from;
    private Station to;
    private SeatType seatType;
    private int ticketAmount;

    public Ticket(LocalDate departDate, Station from, Station to, SeatType seatType, int ticketAmount) {
        this.departDate = departDate;
        this.from = from;
        this.to = to;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public Ticket() {

    }

    // Getters
    public LocalDate getDepartDate() {
        return departDate;
    }

    public Station getFrom() {
        return from;
    }

    public Station getTo() {
        return to;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    // Setters
    public void setDepartDate(LocalDate departDate) {
        this.departDate = departDate;
    }

    public void setFrom(Station from) {
        this.from = from;
    }

    public void setTo(Station to) {
        this.to = to;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }
}