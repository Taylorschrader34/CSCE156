package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.*;
import entities.Member.Member;
import entities.Products.Product;

public class JsonWriter {

	public void jsonPersonConverter(List<Person> persons) {

		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Persons.json");

		PrintWriter jsonPrintWriter = null;

		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		for(Person aPerson : persons) {
			// Use toJson method to convert Person object into a String
			String personOutput = gson.toJson(aPerson); 
			jsonPrintWriter.write(personOutput + "\n");
		}

		jsonPrintWriter.close();
	}
	
	public void jsonProductConverter(List<Product> products) {

		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Products.json");

		PrintWriter jsonPrintWriter = null;

		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		for(Product aProducts : products) {
			// Use toJson method to convert Person object into a String
			String productOut = gson.toJson(aProducts); 
			jsonPrintWriter.write(productOut + "\n");
		}

		jsonPrintWriter.close();
	}
	
	public void jsonMemberConverter(List<Member> members) {

		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Members.json");

		PrintWriter jsonPrintWriter = null;

		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		for(Member aMembers : members) {
			// Use toJson method to convert Person object into a String
			String memberOut = gson.toJson(aMembers); 
			jsonPrintWriter.write(memberOut + "\n");
		}

		jsonPrintWriter.close();
	}
}
