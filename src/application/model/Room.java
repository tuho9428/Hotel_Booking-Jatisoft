package application.model;

import java.time.LocalDate;


public class Room {
	private int roomNumber;
	private String roomType;
	private int price;
	private int availabilityStatus;

	public Room(int roomNumber, String roomType, int price, int availabilityStatus) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
		this.availabilityStatus = availabilityStatus;
	}

	public Room(int roomNumber, String roomType, int price) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
	}

	public boolean isAvailable() {
		return availabilityStatus == 1;
	}
	
	public boolean isAvailable(LocalDate checkInDate,LocalDate checkOutDate) {
		return availabilityStatus == 1;
	}

	public String getDetails() {
		return "Room Number: " + roomNumber + "\nRoom Type: " + roomType + "\nPrice per Night: " + price
				+ "\nAvailability: " + availabilityStatus;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	public void setAvailabilityStatus(int availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public int getAvailabilityStatus() {
		return this.availabilityStatus;
	}
	
	public String toString() {
		return 	roomNumber + " " + roomType + " " +  availabilityStatus;
	}

	public String getCapacity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
