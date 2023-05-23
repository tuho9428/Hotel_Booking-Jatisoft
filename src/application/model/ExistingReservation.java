package application.model;

import java.sql.Date;

public class ExistingReservation extends Reservation {
	
	private String reservationNumber;
    private Room room;
    private double totalPrice;

    private Date checkInDate;
    private Date checkOutDate;
    private int bookingNumber;
    
    // Constructor
    public ExistingReservation(String guestId, int bookingId, Date checkInDate, Date checkOutDate, int bookingNumber) {
        super(guestId, bookingId);
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingNumber = bookingNumber;
    }
    
    public ExistingReservation(String guestId, int bookingId, int adults, int children, Date checkInDate, Date checkOutDate,
            int roomNumber, int bookingNumber) {
    	super(guestId, bookingId);
    	Reservation.adults = adults;
    	Reservation.children = children;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        Reservation.roomNumber = roomNumber;
        this.bookingNumber = bookingNumber;
    }


	public ExistingReservation(String reservationNumber,Room room, double totalPrice, Guest guest) {
		super(guest);
		this.reservationNumber = reservationNumber;
		this.room = room;
		this.totalPrice = totalPrice;
	}

	


    
	public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
    	this.reservationNumber = reservationNumber;
    }

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	public String toString() {
		return 	guest.getFirstName() + " " + guest.getLastName() +
		" Room number: " + reservationNumber + " "+ room.getRoomType();

	}
	
	//
	 public String getGuestId() {
         return guestId;
     }

     public void setGuestId(String guestId) {
         this.guestId = guestId;
     }

     public int getBookingId() {
         return bookingId;
     }

     public void setBookingId(int bookingId) {
         this.bookingId = bookingId;
     }

     public int getAdults() {
         return adults;
     }

     public void setAdults(int adults) {
         Reservation.adults = adults;
     }

     public int getChildren() {
         return children;
     }

     public void setChildren(int children) {
         Reservation.children = children;
     }

     public Date getCheckInDate() {
         return checkInDate;
     }

     public void setCheckInDate(Date checkInDate) {
         this.checkInDate = checkInDate;
     }

     public Date getCheckOutDate() {
         return checkOutDate;
     }

     public void setCheckOutDate(Date checkOutDate) {
         this.checkOutDate = checkOutDate;
     }

     public int getRoomNumber() {
         return roomNumber;
     }

     public void setRoomNumber(int roomNumber) {
         Reservation.roomNumber = roomNumber;
     }

     public int getBookingNumber() {
         return bookingNumber;
     }

     public void setBookingNumber(int bookingNumber) {
         this.bookingNumber = bookingNumber;
     }
	
}