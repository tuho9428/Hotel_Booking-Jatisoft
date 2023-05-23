package application.model;

public class Guest {
	private String prefix;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private Address address;

	public Guest(String prefix, String firstName, String middleInitial, String lastName, String phoneNumber,
			String emailAddress, Address address) {
		this.prefix = prefix;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.address = address;
	}
	
	public Guest(String prefix, String firstName, String middleInitial, String lastName, String phoneNumber,
			String emailAddress) {
		this.prefix = prefix;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}


	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return this.middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address =  address;
		
	}

	public Reservation[] getReservations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return prefix + " " + firstName + " " +  
				middleInitial + " " + lastName + " " + 
				phoneNumber + " " + emailAddress;
	}

//	public int setAdults(int i) {
//		return RoomReservation.getAdults();
//		
//	}
//
//	public int setChildren(int i) {
//		return RoomReservation.getChildren();
//		
//	}
}
