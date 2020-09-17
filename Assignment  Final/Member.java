package entities.Member;

import java.util.List;

import entities.Address;
import entities.Invoice;
import entities.Person;
import entities.Products.*;

//Abstract class
public abstract class Member {
	
	//Encapsulates variables
	protected String memberCode;
	protected String type;
	protected String memberName;
	protected Person person;
	protected Address address;

	//General member constructor
	public Member(String memberCode, String type, Person person, String memberName, Address address) {
		this.memberCode = memberCode;
		this.type = type;
		this.person = person;
		this.memberName = memberName;
		this.address = address;
	}

	//getters and setters
	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	//abstract methods for definition and specific use based on the type of member, student or general
	public abstract double getSubtotal(Product[] products, int[] a);

	public abstract double getFees();
	
	public abstract double getTaxes();
	
	public abstract double getDiscount();
	
	public abstract double getTotal();
	
}
