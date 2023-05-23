package application.model;

import java.sql.Date;


public class Reservation {
	public static int roomNumber;
	public static int adults;
	public static int children;
	protected Date checkInDate;
	protected Date checkOutDate;
	protected Guest guest;
	private String reservationNumber;
	//
	protected String guestId;
    protected int bookingId;

    // Constructor
    public Reservation(String guestId, int bookingId) {
        this.guestId = guestId;
        this.bookingId = bookingId;
    }

	public Reservation(Date checkInDate, Date checkOutDate, Guest guest) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.guest = guest;
		this.reservationNumber = "";
	}
	
    // Getters
    public String getGuestId() {
        return guestId;
    }
    
    public int getBookingId() {
        return bookingId;
    }
    
    // Setters
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

	public Reservation(Guest guest) {
		this.guest = guest;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(Date CheckOutDate) {
		this.checkOutDate = CheckOutDate;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Date getCheckInDate() {
		return this.checkInDate;
	}

	public Date getCheckOutDate() {
		return this.checkOutDate;
	}

	public Guest getGuest() {
		return this.guest;
	}
	
	public static String generateBookingNumber() {
        // Implementation to generate a unique booking number
        // You can use your own logic here, such as generating a random string or using a counter
        // For simplicity, let's assume it generates a random 6-digit number
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
	
	public String toString() {
		return 	guest.getPhoneNumber() + " " + guest.getLastName() +
		" Date In: " + checkInDate + " Date Out: " + checkOutDate;

	}

	public void setReservationNumber(String bookingNumber) {
		this.reservationNumber = bookingNumber;
		
	}

	public String getReservationNumber() {
		return this.reservationNumber;
	}

	
}
