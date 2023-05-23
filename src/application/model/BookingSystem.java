package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookingSystem {
    private List<Room> bookedRooms;
    private List<Reservation> reservations;
    public BookingSystem(List<Room> availableRooms) {
        this.bookedRooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        new ArrayList<>();
    }

    public BookingSystem() {
		// TODO Auto-generated constructor stub
	}
    

    public BookingSystem(List<Room> availableRooms, Connection connection) {
    }

    
    public List<Room> getBookedRooms() {
        return bookedRooms;
    }


    public static String generateBookingNumber() {
        Random rand = new Random();
        int firstDigit = rand.nextInt(9) + 1; // Generate a random digit from 1 to 9
        String remainingDigits = String.format("%05d", rand.nextInt(100000)); // Generate the remaining 5 digits
        String bookingNumber = String.valueOf(firstDigit) + remainingDigits;
        return bookingNumber;
    }


    public Reservation retrieveBookingDetails(String bookingNumber) {
        for (Reservation reservation : this.reservations) {
            if (reservation.getReservationNumber().equals(bookingNumber)) {
                return reservation;
            }
        }

        return null;
    }

    public String retrieveGuestDetails(String bookingNumber) {
        for (Reservation reservation : this.reservations) {
            if (reservation.getReservationNumber().equals(bookingNumber)) {
                return reservation.getGuest().toString();
            }
        }

        return null;
    }
    

}
