package entities.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import entities.Address;
import entities.Invoice;
import entities.Person;
import entities.Products.Product;

public class General extends Member{

	//Encapsulates variables
	private double taxes;
	private double discount;
	private double total;
	
	//General General citizen constructor
	public General(String memberCode, String type, Person person, String memberName, Address address) {
		super(memberCode, type, person, memberName, address);
		this.memberCode = memberCode;
		this.type = type;
		this.person = person;
		this.memberName = memberName;
		this.address = address;
	}

	//Getters and Setters
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
	
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
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

	public double getSubtotal() {
		return 0.0;
	}

	public double getFees() {
		return 0.0;
	}

	public double getTaxes() {
		return taxes;
	}

	public double getDiscount() {
		return discount;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	//getSubtotal method that takes a product array array of ints that represents the ammount of that product 
	//purchased
	public double getSubtotal(Product[] p, int[] a) {
		int numMemberships=0;
		double out = 0;
		double hasYear = 1.00;

		double firstMonthY = 1.0;
		double firstMonthD = 1.0;
		
		double taxes = 0.0;
		
		for(int i = 0; i < p.length; i++) {
			
			double temp = 0.0;
			
			if(p[i].getMonth() == 1) {
				firstMonthY = 0.85;
				firstMonthD = 0.50;
			}

			if(p[i].getProductType().equals("Y")) {
				hasYear = 0.95;
				numMemberships += a[i] * 365;
				temp = Double.parseDouble(p[i].getCost()) * a[i] * firstMonthY;
				taxes += temp * 0.06;
			}else if(p[i].getProductType().equals("D")) {
				numMemberships += a[i];
				temp = Double.parseDouble(p[i].getCost()) * a[i] * firstMonthD;
				taxes += temp * 0.06;
			}else if(p[i].getProductType().equals("P")) {
				//If purchased less than number of passes
				if(numMemberships >= a[i]) {
					temp = 0;
				} 
				//If purchases more than number of passes
				else { 					
					temp = Double.parseDouble(p[i].getCost()) * a[i];
				}
				taxes += temp * 0.04;
			}else if(p[i].getProductType().equals("R")) {
				temp = Double.parseDouble(p[i].getCost()) * a[i] * hasYear;
				taxes += temp * 0.04;
			}
			out += temp;
			
		}
		setTaxes(taxes);
		setDiscount(0.0);
		setTotal(out + getTaxes() - getDiscount() + getFees());
		return out;
	}

	
}
