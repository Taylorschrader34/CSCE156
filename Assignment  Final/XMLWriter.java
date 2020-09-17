package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import entities.*;
import entities.Member.Member;
import entities.Products.Product;

import com.thoughtworks.xstream.XStream;

public class XMLWriter {
	public static void xmlPersonConverter(List<Person> persons) {
		XStream  xstream = new XStream();
		
		//Creates .xml file
        File xmlOutput = new File("data/Persons.xml");
		
        //Creats print writer
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Pull objects and format into xml
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("person", Person.class); 
		for(Person aPerson : persons) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aPerson);
			xmlPrintWriter.write(personOutput);
		}
		//Close the write
		xmlPrintWriter.close();	
	}
	public static void xmlProductConverter(List<Product> products) {
		XStream  xstream = new XStream();
		
		//Creates .xml file
        File xmlOutput = new File("data/Products.xml");
		
        //Creats print writer
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Pull objects and format into xml
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("product", Product.class); 
		for(Product aProduct : products) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aProduct);
			xmlPrintWriter.write(personOutput);
		}
		//Close the write
		xmlPrintWriter.close();	
	}
	public static void xmlMemberConverter(List<Member> members) {
		XStream  xstream = new XStream();
		
		//Creates .xml file
        File xmlOutput = new File("data/Members.xml");
		
        //Creats print writer
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Pull objects and format into xml
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("member", Member.class); 
		for(Member aMember : members) {
			// Use toXML method to convert Person object into a String
			String memberOut = xstream.toXML(aMember);
			xmlPrintWriter.write(memberOut);
		}
		//Close the write
		xmlPrintWriter.close();	
	}
}
