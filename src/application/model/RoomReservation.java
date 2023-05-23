package application.model;

public class RoomReservation extends Reservation {
	

	protected Room room;
	private double totalPrice;
	private int adults;
    private int children;
	private int roomNumber;

	
    // Constructor
    public RoomReservation(String guestId, int bookingId, int adults, int children, int roomNumber) {
        super(guestId, bookingId);
        this.adults = adults;
        this.children = children;
        this.roomNumber = roomNumber;
    }
	
	   // Getters
    public int getAdults() {
        return adults;
    }

    public int getChildren() {
        return children;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    // Setters
    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

	public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    } 
    
	public String toString() {
		return 	guest.getFirstName() + " " + guest.getLastName() +
		" Date In: " + checkInDate + " Date Out: " + checkOutDate + " " + room;

	}

}