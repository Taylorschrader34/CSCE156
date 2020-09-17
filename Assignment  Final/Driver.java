package driver;

import java.io.IOException;
import java.util.List;

import writer.InvoiceWriter;
import writer.JsonWriter;
import reader.FlatFileReader;
import writer.XMLWriter;
import entities.*;
import entities.Member.Member;
import entities.Products.Product;

public class Driver {

    public static void main(String[] args) throws IOException {
    	//Opens file reader
        FlatFileReader file = new FlatFileReader();
//        file.loadPersons();
        file.loadProduct();

        //Loads list of persons
        List<Person> personList = file.loadPersons();
        List<Product> productList = file.loadProduct();
        List<Member> memberList = file.loadMembers();
        List<Invoice> invoicesList = file.loadInvoice();
        
		//Writes Person ArrayList into a Json and XML files
		JsonWriter jWriter = new JsonWriter();
		jWriter.jsonPersonConverter(personList);
        XMLWriter.xmlPersonConverter(personList);
        
		//Writes Product ArrayList into a Json and XML files
		jWriter.jsonProductConverter(productList);
        XMLWriter.xmlProductConverter(productList);
        
		//Writes Product ArrayList into a Json and XML files
		jWriter.jsonMemberConverter(memberList);
        XMLWriter.xmlMemberConverter(memberList);
        
        //Writes Invoice ArrayList into a readable text file
        InvoiceWriter.invoiceConverter(invoicesList);
        
    }
	
}
