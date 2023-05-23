package application.model;

public class Address {
	private String country;
	private String address1;
	private String address2;
	private String city;
	private String zipCode;
	private String additionalDetails;

	public Address( String country, String address1, String address2, 
			String city, String zipCode, String additionalDetails) {
		this.country = country;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.zipCode = zipCode;
		this.additionalDetails = additionalDetails;
	}
	
	public Address( String country, String address1,String city, String zipCode, String additionalDetails) {
		this.country = country;
		this.address1 = address1;
		this.address2 = "";
		this.city = city;
		this.zipCode = zipCode;
		this.additionalDetails = additionalDetails;
	}
	

	public Address(String country, String address1, String city, String zipCode) {
		this.country = country;
		this.address1 = address1;
		this.address2 = "";
		this.city = city;
		this.zipCode = zipCode;
		this.additionalDetails = "";
	}



	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	
	public String toString() {
		return 	address1 + " " + address2 + " " +  
				city + " " + zipCode + " " + country  + " " + 
				"\n Additional Details: " + additionalDetails;
	}
}
