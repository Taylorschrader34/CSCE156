package writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import entities.Invoice;

public class InvoiceWriter {

	/*
	 * 
	 * Takes a list of invoices and writes them to a formated file with both the general 
	 * overall purchases information and each individual transaction
	 * 
	 */
	
	public static void invoiceConverter(List<Invoice> invoices) {

		File invoiceOutput = new File("data/Output.txt");

		PrintWriter invoicePrintWriter = null;

		try {
			invoicePrintWriter = new PrintWriter(invoiceOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 

		String format = "%-10s %-40s %-30s %9s %9s %9s %9s %9s\n";

		/*
		 * 
		 * SUMMARY STATEMENT
		 * 
		 */

		invoicePrintWriter.write("Executive Summary Report\n");
		invoicePrintWriter.write("=========================\n");
		String header = String.format(format, "Invoice", "Member", "Personal Trainer", "Subtotal", "Fees", "Taxes", "Discount", "Total");
		invoicePrintWriter.write(header);

		String ind = null;

		double finalSub = 0, finalFee = 0, finalTax = 0, finalDiscount = 0, finalTotal = 0;
		
		for(Invoice anInvoice : invoices) {
			
			double sub = anInvoice.getMember().getSubtotal(anInvoice.getProducts(), anInvoice.getAmount());
			double fee = anInvoice.getMember().getFees();
			double tax = anInvoice.getMember().getTaxes();
			double disc = anInvoice.getMember().getDiscount();
			double totl = anInvoice.getMember().getTotal();
			
			String memType = "General";
			if(anInvoice.getMember().getType().equals("S")) {
				memType = "Student";
			}
			
			ind = (String.format(format, anInvoice.getInvCode(),
					anInvoice.getMember().getMemberName() + " [" + memType + "]",
					anInvoice.getTrainer().getlastFirst(),
					(double)Math.round(sub * 100d) / 100d,
					(double)Math.round(fee * 100d) / 100d,
					(double)Math.round(tax * 100d) / 100d,
					-(double)Math.round(disc * 100d) / 100d,
					(double)Math.round(totl * 100d) / 100d));

			finalSub += sub;
			finalFee += fee;
			finalTax += tax;
			finalDiscount += disc;
			finalTotal += totl;
			
			invoicePrintWriter.write(ind);

		}

		invoicePrintWriter.write("=======================================================================================================================================\n");
		
		ind = (String.format(format,"TOTALS",
				"",
				"",
				(double)Math.round(finalSub * 100d) / 100d,
				(double)Math.round(finalFee * 100d) / 100d,
				(double)Math.round(finalTax * 100d) / 100d,
				-(double)Math.round(finalDiscount * 100d) / 100d,
				(double)Math.round(finalTotal * 100d) / 100d));
		
		invoicePrintWriter.write(ind);
		
		
		/*
		 * 
		 * INDIVIDUAL STATEMENT
		 * 
		 */

		String f = "%-10s %-60s %9s %9s %9s\n";
		String f2 = "%-10s %-60s %9s %9s %9s\n%-10s %-60s\n";

		invoicePrintWriter.write("\nIndividual Invoice Detail Reports\n");
		invoicePrintWriter.write("=================================\n");

		for(Invoice anInvoice : invoices) {
			
			String memType = "General";
			if(anInvoice.getMember().getType().equals("S")) {
				memType = "Student";
			}
			
			invoicePrintWriter.write("Invoice " + anInvoice.getInvCode() + "\n");
			invoicePrintWriter.write("====================");
			invoicePrintWriter.write("\nPersonal Trainer: " + anInvoice.getTrainer().getlastFirst());
			invoicePrintWriter.write("\nMember Info: ");
			invoicePrintWriter.write("\n\t" + anInvoice.getMember().getMemberName() + "(" + anInvoice.getMember().getMemberCode() + ")");
			invoicePrintWriter.write("\n\t[" + memType +"]");
			invoicePrintWriter.write("\n\t" + anInvoice.getMember().getPerson().getlastFirst());
			invoicePrintWriter.write("\n\t" + anInvoice.getMember().getAddress().getHouse());
			invoicePrintWriter.write("\n\t" + anInvoice.getMember().getAddress().getCity() + " " +
					anInvoice.getMember().getAddress().getState() + " " +
					anInvoice.getMember().getAddress().getZip() + " " +
					anInvoice.getMember().getAddress().getCountry());
			invoicePrintWriter.write("\n--------------------------\n");

			ind = (String.format(f, "Code", "Item", "SubTotal", "Tax", "Total"));
			invoicePrintWriter.write(ind);

			int numMemberships=0;
			String memCode = null;
			double hasYear = 1.00;

			/*
			 * 
			 * PRODUCT LIST
			 * 
			 */
			
			for(int j = 0; j < anInvoice.getProducts().length; j++) {

				memCode = anInvoice.getProducts()[0].getProductCode();

				double firstMonthY = 1.0;
				double firstMonthD = 1.0;
				if(anInvoice.getProducts()[j].getMonth() == 1) {
					firstMonthY = 0.85;
					firstMonthD = 0.50;
				}

				//If student
				if(anInvoice.getMember().getType().equals("S")) {
					
					//If yearpass
					if(anInvoice.getProducts()[j].getProductType().equals("Y")){
						hasYear = 0.95;
						numMemberships += anInvoice.getAmount()[j] * 365;
						double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * firstMonthY;
						double b = a * 0.06;
						//If purchased in january
						if(anInvoice.getProducts()[j].getMonth() == 1) {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
									a,
									b,
									a + b, "" , anInvoice.getProducts()[j].getStartDate() + " - " + anInvoice.getProducts()[j].getEndDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with %15 off)"));
						} 
						//If purchased any other time
						else { ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
								anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
								a,
								(double)Math.round(b * 100d) / 100d,
								(double)Math.round((a + b) * 100d) / 100d, "" , anInvoice.getDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost()));}
					} 
					//If daypass
					else if(anInvoice.getProducts()[j].getProductType().equals("D")){
						numMemberships += anInvoice.getAmount()[j];
						double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * firstMonthD;
						double b = a * 0.06;
						//If purchased in January
						if(anInvoice.getProducts()[j].getMonth() == 1) {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((double)Math.round((a + b) * 100d) / 100d * 100d) / 100d,
									"", anInvoice.getProducts()[j].getDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with %50 off)"));}
						//If purchased in any other month
						else {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((a+b) * 100d) / 100d,
									"", anInvoice.getProducts()[j].getDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + ")"));
						}
					} 
					//If parking pass
					else if(anInvoice.getProducts()[j].getProductType().equals("P")){
						//If purchased less than number of passes
						if(numMemberships >= anInvoice.getAmount()[j]) {
							ind = (String.format(f, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " " + memCode + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with " + anInvoice.getAmount()[j] + " free)",
									0.00,
									0.00 ,
									0.00));
						} 
						//If purchased more than number of passes
						else { 					
							double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j];
							double b = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * 0.04;
							ind = (String.format(f, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " " + memCode + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with " + numMemberships + " free)",
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((a+b) * 100d) / 100d));
						}
					} 
					//If rental
					else if(anInvoice.getProducts()[j].getProductType().equals("R")){
						double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * hasYear;
						double b = a * 0.04;
						//If january
						if(anInvoice.getProducts()[j].getMonth() == 1) {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " - " + memCode + " - " + anInvoice.getProducts()[j].getName(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d ,
									(double)Math.round((a+b) * 100d) / 100d,
									"", "(" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + "/unit @ %5 off)"));
						} 
						//If other month
						else { ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
								anInvoice.getProducts()[j].getProductType() + " - " + memCode + " - " + anInvoice.getProducts()[j].getName(),
								(double)Math.round(a * 100d) / 100d,
								(double)Math.round(b * 100d) / 100d ,
								(double)Math.round((a+b) * 100d) / 100d,
								"", "(" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + "/unit)"));
						}
					}
					invoicePrintWriter.write(ind);
				} 
				//If general
				else {
					//If yearpass
					if(anInvoice.getProducts()[j].getProductType().equals("Y")){
						hasYear = 0.95;
						numMemberships += anInvoice.getAmount()[j] * 365;
						double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * firstMonthY;
						double b = a * 0.06;
						//If january
						if(anInvoice.getProducts()[j].getMonth() == 1) {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((a+b) * 100d) / 100d, "" , anInvoice.getProducts()[j].getStartDate() + " - " + anInvoice.getProducts()[j].getEndDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with %15 off)"));
						} 
						//if other month
						else { ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
								anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
								(double)Math.round(a * 100d) / 100d,
								(double)Math.round(b * 100d) / 100d,
								(double)Math.round((a+b) * 100d) / 100d, "" , anInvoice.getDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost()));
						}
					} 
					//If daypass
					else if(anInvoice.getProducts()[j].getProductType().equals("D")){
						numMemberships += anInvoice.getAmount()[j];
						double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * firstMonthD;
						double b = a * 0.06;
						//If January
						if(anInvoice.getProducts()[j].getMonth() == 1) {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((a + b) * 100d) / 100d,
									"", anInvoice.getProducts()[j].getDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with %50 off)"));}
						//If other month
						else {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " @ " + anInvoice.getProducts()[j].getAddress().getHouse(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((a + b) * 100d) / 100d,
									"", anInvoice.getProducts()[j].getDate() + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + ")"));
						}
					} 
					//If parking pass
					else if(anInvoice.getProducts()[j].getProductType().equals("P")){
						//If purchased less than number of passes
						if(numMemberships >= anInvoice.getAmount()[j]) {
							ind = (String.format(f, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " " + memCode + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with " + anInvoice.getAmount()[j] + " free)",
									0.00,
									0.00 ,
									0.00));
						} 
						//If purchases more than number of passes
						else { 					
							double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j];
							double b = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * 0.04;
							ind = (String.format(f, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " " + memCode + " (" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + " with " + numMemberships + " free)",
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d,
									(double)Math.round((a + b) * 100d) / 100d));
						}
					} 
					//If rental
					else if(anInvoice.getProducts()[j].getProductType().equals("R")){
						double a = Double.parseDouble(anInvoice.getProducts()[j].getCost()) * anInvoice.getAmount()[j] * hasYear;
						double b = a * 0.04;
						//If purchased with yearpass
						if(anInvoice.getProducts()[j].getMonth() == 1) {
							ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
									anInvoice.getProducts()[j].getProductType() + " - " + memCode + " - " + anInvoice.getProducts()[j].getName(),
									(double)Math.round(a * 100d) / 100d,
									(double)Math.round(b * 100d) / 100d ,
									(double)Math.round((a + b) * 100d) / 100d,
									"", "(" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + "/unit @ %5 off)"));
						} 
						//If purchased without pass
						else { ind = (String.format(f2, anInvoice.getProducts()[j].getProductCode(),
								anInvoice.getProducts()[j].getProductType() + " - " + memCode + " - " + anInvoice.getProducts()[j].getName(),
								(double)Math.round(a * 100d) / 100d,
								(double)Math.round(b * 100d) / 100d ,
								(double)Math.round((a + b) * 100d) / 100d,
								"", "(" + anInvoice.getAmount()[j] + " units @ $" + anInvoice.getProducts()[j].getCost() + "/unit)"));
						}
					}
					//Print whichever string was formed
					invoicePrintWriter.write(ind);
				}
			}
			//Print totals of columns
			invoicePrintWriter.write(String.format(f, "","", "  =====================================", "", ""));
			invoicePrintWriter.write(String.format(f, "SUB-TOTALS","", (double)Math.round(anInvoice.getMember().getSubtotal(anInvoice.getProducts(), anInvoice.getAmount()) * 100d) / 100d, (double)Math.round(anInvoice.getMember().getTaxes() * 100d) / 100d, (double)Math.round(( anInvoice.getMember().getTotal() - anInvoice.getMember().getFees() + anInvoice.getMember().getDiscount()) * 100d) / 100d));
			if(anInvoice.getMember().getType().equals("S")) {
				invoicePrintWriter.write(String.format(f, "DISCOUNT (8% STUDENT & NO TAX)","", "", -(double)Math.round(anInvoice.getMember().getDiscount() * 100d) / 100d, ""));
				invoicePrintWriter.write(String.format(f, "ADDITIONAL FEE (Student)","", "", (double)Math.round(anInvoice.getMember().getFees() * 100d) / 100d, ""));
			}
			invoicePrintWriter.write(String.format(f, "TOTAL","", "", "", (double)Math.round(anInvoice.getMember().getTotal() * 100d) / 100d));
			invoicePrintWriter.write("\n\t\t\t Thank you for your purchase!\n\n\n");
		}
		
		invoicePrintWriter.close();
	}
}
