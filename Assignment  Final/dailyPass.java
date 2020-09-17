package entities.Products;

import entities.Address;

//Class inherits Product
public class dailyPass extends Product{

	//General Daily Pass Constructor
	public dailyPass(String productCode, String productType,String date, Address address, String cost) {
		super(productCode, productType, cost);
		// TODO Auto-generated constructor stub
		this.date = date;
		this.address = address;
	}

	//Getters and Setters
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCost() {
		return cost;
	}

	//gets the month that the daily pass was purchased in
	public int getMonth() {
		String a[] = date.split("-");
		return Integer.parseInt(a[1]);
	}


}
