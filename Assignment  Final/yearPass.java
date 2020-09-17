package entities.Products;

import entities.Address;

//Class inherits Product
public class yearPass extends Product{

	//General year pass constructor
	public yearPass(String productCode, String productType, String startDate, String endDate, Address address, String name, String cost) {
		super(productCode, productType, cost);
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.name = name;
	}

	//getters and setters
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCost() {
		return cost;
	}

	//gets the month that the daily pass was purchased in
	public int getMonth() {
		String a[] = startDate.split("-");
		return Integer.parseInt(a[1]);
	}

}
