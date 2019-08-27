package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address,
 * description, quantity, price and amount. It also calculates the sales tax @
 * 10% and prints as part of order. It computes the total order amount (amount
 * of individual lineItems + total sales tax) and prints it.
 */
public class OrderReceipt {
	private Order order;
	private StringBuilder output;

	public OrderReceipt(Order order) {
		this.order = order;
		this.output = new StringBuilder();
	}

	private void printHeaders() {
		output.append("======Printing Orders======\n");
	}

	private void printCustomerInfo() {
		output.append(order.getCustomerName());
		output.append(order.getCustomerAddress());
	}

	private void printsLineItems() {
		double totSalesTx = 0d;
		double tot = 0d;

		for (LineItem lineItem : order.getLineItems()) {
			output.append(lineItem.getDescription());
			output.append('\t');
			output.append(lineItem.getPrice());
			output.append('\t');
			output.append(lineItem.getQuantity());
			output.append('\t');
			output.append(lineItem.totalAmount());
			output.append('\n');
			
			double salesTax = calculateSalesTax( lineItem) ;
			totSalesTx = calculateTotalSalesTax(totSalesTx, salesTax);
			tot = calculateTotalamountoFlineItem(salesTax,lineItem,tot);
		}
		printsStateTax(totSalesTx);
		printTotalAmount(tot);
	}
	
	private double calculateSalesTax( LineItem lineItem) {
		double taxRate = .10;
		double salesTax = lineItem.totalAmount() * taxRate;
		return salesTax;
	}
	
	private double calculateTotalSalesTax(double totSalesTx, double salesTax) {
		totSalesTx += salesTax;	
		return totSalesTx;
	}
	
	
	private double calculateTotalamountoFlineItem(double salesTax, LineItem lineItem, double tot) {
		tot += lineItem.totalAmount() + salesTax;
		return tot;
	}
	

	private void printsStateTax(double totSalesTx) {
		output.append("Sales Tax").append('\t').append(totSalesTx);
	}

	private void printTotalAmount(double tot) {
		output.append("Total Amount").append('\t').append(tot);
	}

	public String printReceipt() {
		printHeaders();
		printCustomerInfo();
		printsLineItems();
		return output.toString();
	}
}