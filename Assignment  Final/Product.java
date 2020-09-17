package entities.Products;

import entities.Address;

public abstract class Product {

	//Encapsulates variables
	protected String productCode;
	protected String productType;
	protected String startDate;
	protected String endDate;
	protected Address address;
	protected String name;
	protected String cost;
	protected String date;
	
	//General product constructor
	public Product(String productCode, String productType, String cost) {
		this.productCode = productCode;
		this.productType = productType;
		this.cost = cost;
	}
	
	//Getters and setters
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public abstract String getCost();



	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getProductCode() {
		return productCode;
	}



	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public String getProductType() {
		return productType;
	}



	public void setProductType(String productType) {
		this.productType = productType;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	
	//abstract method for each products use of this method is different
	public abstract int getMonth();

}
