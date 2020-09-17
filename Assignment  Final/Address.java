package entities;

public class Address {

	//Encapsulates variables
	private String house;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	//General Address constructor
	public Address(String house, String city, String state, String zip, String country) {
		this.house = house;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}
	
	//Getters and Setters
	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
