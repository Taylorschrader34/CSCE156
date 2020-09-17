package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import entities.*;
import entities.Member.*;
import entities.Products.*;

public class FlatFileReader {

	public ArrayList<Person> loadPersons() {
		//Sets a scanner to null
		Scanner s = null;
		try {
			//Creates scanner to read file and then scans first line
			s = new Scanner(new File("data/Persons.dat"));
			s.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Creates list to store persons
		ArrayList<Person> personList = new ArrayList<Person>();

		//While the file has more information
		while(s.hasNext()) {
			//Create email fiels
			String[] emails = null;

			//Get line and break into tokens
			String line = s.nextLine();
			String tokens[] = line.split(";");

			//Get information from the line and stores it in variables
			String personCode = tokens[0];
			String names[] = tokens[1].split(",");
			Name name = new Name(names[1], names[0]);
			String adds[] = tokens[2].split(",");
			Address address = new Address(adds[0], adds[1], adds[2], adds[3], adds[4]);

			//If the person has email(s), add them
			if(tokens.length == 4) {
				emails = tokens[3].split(",");
			}

			//Builds person using information from line and then adds to list
			Person p = new Person(personCode, address, emails, name);
			personList.add(p);
		}	

		//Returns list of persons
		return personList;

	}

	public ArrayList<Member> loadMembers() {
		//Sets a scanner to null
		Scanner s = null;
		try {
			//Creates scanner to read file and then scans first line
			s = new Scanner(new File("data/Members.dat"));
			s.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Creates list to store persons
		ArrayList<Member> memberList = new ArrayList<Member>();

		//While the file has more information
		while(s.hasNext()) {

			//Get line and break into tokens
			String line = s.nextLine();
			String tokens[] = line.split(";");

			//Get information from the line and stores it in variables
			String memberCode = tokens[0];
			String type = tokens[1];
			String personCode = tokens[2];

			Person person = personCodeSearch(personCode);

			String memberName = tokens[3];
			String adds[] = tokens[4].split(",");
			Address address = new Address(adds[0], adds[1], adds[2], adds[3], adds[4]);

			//Builds person using information from line and then adds to list
			Member m = null;
			if(type.equals("S")) {
				m = new Student(memberCode, type, person, memberName, address);
			}else {
				m = new General(memberCode, type, person, memberName, address);
			}
			

			memberList.add(m);
		}	

		//Returns list of persons
		return memberList;

	}

	public ArrayList<Product> loadProduct() {
		//Sets a scanner to null
		Scanner s = null;
		try {
			//Creates scanner to read file and then scans first line
			s = new Scanner(new File("data/Products.dat"));
			s.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Creates list to store persons
		ArrayList<Product> productList = new ArrayList<Product>();

		//While the file has more information
		while(s.hasNext()) {

			//Get line and break into tokens
			String line = s.nextLine();
			String tokens[] = line.split(";");

			if(tokens[1].equals("Y")) {
				String productCode = tokens[0];
				String productType = tokens[1];
				String startDate = tokens[2];
				String endDate = tokens[3];
				String adds[] = tokens[4].split(",");
				Address address = new Address(adds[0], adds[1], adds[2], adds[3], adds[4]);
				String name = tokens[5];
				String cost = tokens[6];

				//Builds person using information from line and then adds to list
				yearPass p = new yearPass(productCode, productType, startDate, endDate, address, name, cost);
				productList.add(p);
			}else if(tokens[1].equals("D")){
				String productCode = tokens[0];
				String productType = tokens[1];
				String date = tokens[2];
				String adds[] = tokens[3].split(",");
				Address address = new Address(adds[0], adds[1], adds[2], adds[3], adds[4]);
				String cost = tokens[4];

				//Builds person using information from line and then adds to list
				dailyPass p = new dailyPass(productCode, productType, date, address,cost);
				productList.add(p);
			}else if(tokens[1].equals("P")){
				String productCode = tokens[0];
				String productType = tokens[1];
				String cost = tokens[2];

				//Builds person using information from line and then adds to list
				parkingPass p = new parkingPass(productCode, productType, cost);
				productList.add(p);
			}else if(tokens[1].equals("R")){
				String productCode = tokens[0];
				String productType = tokens[1];
				String name = tokens[2];
				String cost = tokens[3];

				//Builds person using information from line and then adds to list
				equipment p = new equipment(productCode, productType, name, cost);
				productList.add(p);
			}else{
				System.out.println("Invalid product type");
			}
		}	

		//Returns list of persons
		return productList;

	}

	public ArrayList<Invoice> loadInvoice() {
		//Sets a scanner to null
		Scanner s = null;
		try {
			//Creates scanner to read file and then scans first line
			s = new Scanner(new File("data/Invoices.dat"));
			s.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Creates list to store persons
		ArrayList<Invoice> InvoiceList = new ArrayList<Invoice>();

		//While the file has more information
		while(s.hasNext()) {

			//Get line and break into tokens
			String line = s.nextLine();
			String tokens[] = line.split(";");

			//Get information from the line and stores it in variables
			String invCode = tokens[0];
			String memberCode = tokens[1];
			String trainerCode = tokens[2];
			String date = tokens[3];
			String productsTemp[] = tokens[4].split(",");
			Product[] products = new Product[productsTemp.length];
			int[] amount = new int[productsTemp.length];
			
			for(int i = 0; i < productsTemp.length; i++) {
				String temp[] = productsTemp[i].split(":");
				products[i] = productCodeSearch(temp[0]);
				amount[i] = Integer.parseInt(temp[1]);
			}
			
			Member member = memberCodeSearch(memberCode);
			Person trainer = personCodeSearch(trainerCode);	

			//Builds person using information from line and then adds to list
			Invoice inv = new Invoice(invCode, member, trainer, date, products, amount);
			InvoiceList.add(inv);
		}	
		//Returns list of persons
		return InvoiceList;
	}	
	
	/**
	 * 
	 * Search methods
	 * 
	 */
	
	//Gets member with given member code
	public Member memberCodeSearch(String term){
		Member out = null;
		for(Member m : this.loadMembers()) {
			if(m.getMemberCode().equals(term)) {
				out = m;
			}
		}
		return out;

	}
	
	//Gets person with given person code
	public Person personCodeSearch(String term){
		Person out = null;
		for(Person p : this.loadPersons()) {
			if(p.getPersonCode().equals(term)) {
				out = p;
			}
		}
		return out;

	}
	
	//Gets product with given product code
	public Product productCodeSearch(String term){
		Product out = null;
		for(Product p : this.loadProduct()) {
			if(p.getProductCode().equals(term)) {
				out = p;
			}
		}
		return out;

	}
	
}
