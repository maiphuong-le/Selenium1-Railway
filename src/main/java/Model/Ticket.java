package Model;

import lombok.Data;
import DataTypes.SeatType;
import DataTypes.Station;

import java.time.LocalDate;

@Data
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
}