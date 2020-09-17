package entities;

import entities.Member.Member;
import entities.Products.Product;

public class Invoice {

	//Encapsulates variables
	private String invCode;
	private String date;
	private Member member;
	private Person trainer;
	private Product products[];
	private int amount[];

	//General invoice constructor
	public Invoice(String invCode, Member member, Person trainer, String date, Product[] products, int[] amount) {
		this.invCode = invCode;
		this.date = date;
		this.member = member;
		this.trainer = trainer;
		this.products = products;
		this.amount = amount;
	}

	//getters and setters
	public String getInvCode() {
		return invCode;
	}

	public void setInvCode(String invCode) {
		this.invCode = invCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Person getTrainer() {
		return trainer;
	}

	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}

	public Product[] getProducts() {
		return products;
	}

	public void setProducts(Product[] products) {
		this.products = products;
	}
	
	public int[] getAmount() {
		return amount;
	}

	public void setAmount(int[] amount) {
		this.amount = amount;
	}
	
}
