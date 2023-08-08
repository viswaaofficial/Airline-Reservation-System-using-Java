package Project;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class DomesticFlight extends Flight {
    public DomesticFlight(String flightNumber, String destination, int seats, Date departureTime,
                          Date arrivalTime, int duration) {
        super(flightNumber, destination, seats, departureTime, arrivalTime, duration);
    }

    @Override
    public double getTicketPrice() {
        // Calculate ticket price based on flight duration and additional fees
        return 100.00 + (getDuration() * 0.1);
    }
}
