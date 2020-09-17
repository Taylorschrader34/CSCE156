package entities;

public class Person {

	//Encapsulates variables
	private String personCode;
	private Address address;
	private String[] email;
	private Name name;
	 
	//General person constructor
	public Person(String personCode, Address address, String[] email, Name name) {
		this.personCode = personCode;
		this.address = address;
		this.email = email;
		this.name = name;
	}

	//getters and setters
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String[] getEmail() {
		return email;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	//method that gives a formated name in "Last, First" format
	public String getlastFirst() {
		return (name.getLastName() +", " + name.getFirstName());
	}
	
	
}
