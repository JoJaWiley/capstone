package zootix;
import java.io.*;
import java.util.*;

public class TestDriver {
	
	//method for getting quantities of each type of ticket in user's basket, along with subtotals for each type. Stored in 2D array.
	public static double[][] quantityTotal(ArrayList<Ticket> tix) {
		double sumChild = 0;
		double childTotal = 0;
		double sumRegular = 0;
		double regularTotal = 0;
		double sumSenior = 0;
		double seniorTotal = 0;
		
		//for each ticket - decide which type, then increase the sum and subtotal for that type.
		for (Ticket tick : tix)
			switch(tick.getType())
			{
			case "Child" -> {sumChild++; childTotal+= tick.getPrice();}
			case "Regular" -> {sumRegular++; regularTotal+= tick.getPrice();}
			case "Senior" -> {sumSenior++; seniorTotal+= tick.getPrice();}
			}
		
		//name the thing (2D array for subtotals)
		double[][] qT = {{sumChild, sumRegular, sumSenior}, {childTotal, regularTotal, seniorTotal}};
		
		//return the thing
		return qT;
	}

	public static void main(String[] args) {
		//test driver for zootix
		
		//regular price variable. discounts are built into setters for Senior and Child classes
		double price = 16.50;
		
		//create scanner
		Scanner scanner = new Scanner(System.in);
		
		//have the user enter first and last name
		System.out.println("Please enter your first and last name: ");
		
		//scanner is whitespace delimited, so use that to parse first & last, & then reconstruct the full name in the name variable
		String firstName = scanner.next();
		String lastName = scanner.next();
		String name = firstName + " " + lastName;
		
		//get the one possible TicketFactory instance to create ticket objects
		TicketFactory tixFactory = TicketFactory.getInstance();
		
		//create an array to implement the user's basket of tickets
		ArrayList<Ticket> tixBasket = new ArrayList<Ticket>();
		
		//total price of items in user's basket
		double basketTotal = 0;
		
		//string to display what's in the basket
		String sayBasket = "";
		
		//string to hold answer to "add or checkout" question
		String answer = "";
		
		//greet the dang user
		System.out.printf("Welcome to ZooTix, %s!", name);
		
		//do while loop to keep asking if they want more
		do {
			
			//ask which type they want to add to basket
			System.out.printf("\nPlease choose which item you would like to add to your basket: \n[Enter C] Child ticket for children 12 and under (price: $%.2f)", price - 2);
			System.out.printf("\n[Enter R] Regular ticket for agaes over 12 and less than 65 (price: $%.2f)", price);
			System.out.printf("\n[Enter S] Senior ticket for seniors 65 and up (price: $%.2f)", price - 1);
			
			//store user's choice in variable
			String choice = scanner.next().substring(0, 1);
			
			//create ticket of user selected type, using ticket factory class. Ticket String attribute "type" is determined by this choice, as are discounts.
			Ticket ticket = tixFactory.getTicket(choice);
			
			//set the regular price. discounts are built into this method for Child and Senior classes.
			ticket.setPrice(price);
			//add ticket to basket
			tixBasket.add(ticket);
			//update basket total
			basketTotal+= ticket.getPrice();
			//update string for basket contents
			sayBasket = sayBasket + "1 " + ticket.getType() + " ticket. ";
			
			//display a running list of items in user's basket, along with running total
			System.out.println("Your basket now includes: " + sayBasket);
			System.out.printf("Your current total: $%.2f\n", basketTotal);
			
			//have user choose whether to add more to basket or checkout
			System.out.println("[Enter A] Add more tickets to basket");
			System.out.println("[Enter C] Checkout");
			
			//store user's choice in a variable
			answer = scanner.next().substring(0, 1);
			
		} while (answer.equalsIgnoreCase("a"));
		
		//2D array of quantities and total prices. rows index quantity or total price for each type. columns index types. child, regular, or senior.
		double[][] qT = quantityTotal(tixBasket);
		
		//all the tickets. THAT total.
		int totalQty = (int) (qT[0][0] + qT[0][1] + qT[0][2]);
		
		//total price for all the tickets.
		double totalPrice = qT[1][0] + qT[1][1] + qT[1][2];
		
		//formatted strings for displaying subtotals to user
		String sayBasketChild = String.format("Your basket includes: \n%d Child tickets (subtotal: $%.2f)", (int) qT[0][0], qT[1][0]);
		String sayBasketRegular = String.format("%d Regular tickets (subtotal: $%.2f)", (int) qT[0][1], qT[1][1]);
		String saySenior = String.format("%d Senior tickets (subtotal: $%.2f)", (int) qT[0][2], qT[1][2]);
		
		//display subtotals to user
		System.out.println(sayBasketChild);
		System.out.println(sayBasketRegular);
		System.out.println(saySenior);
		System.out.println("--------------------------------");
		
		//display totals to user
		System.out.printf("\n%d tickets (total: $%.2f)", totalQty, totalPrice);
		
		//start try catch for creating receipt
		try {
			
			//instantiate a BufferedWriter for this
			BufferedWriter writer = new BufferedWriter(new FileWriter("TicketOrder.txt"));
			
			//formatted header with user's name
			String header = String.format("Receipt for %s" , name);
			
			//write the header
			writer.write(header);
			writer.write("\n=================================");
			
			//format subtotals for printing to receipt
			String childSubtotal = String.format("\n%d X Child Ticket          $%.2f", (int) qT[0][0], qT[1][0]);
			String regularSubtotal = String.format("\n%d X Regular Ticket        $%.2f", (int) qT[0][1], qT[1][1]);
			String seniorSubtotal = String.format("\n%d X Senior Ticket         $%.2f", (int) qT[0][2], qT[1][2]);
			String total = String.format("\n%d X Total Ticket(s)       $%.2f", totalQty, totalPrice);
			
			//write subtotals to recept
			writer.write(childSubtotal);
			writer.write(regularSubtotal);
			writer.write(seniorSubtotal);
			writer.write("\n---------------------------------");
			writer.write(total);
			writer.write("\n\nThank you for using ZooTix!");
			
			//close the writer
			writer.close();
		
		} catch (IOException e) {
		
			//error message about creating the receipt. because that's what this is about.
			System.out.println("Error with transaction - receipt could not be created.");
			e.printStackTrace();
			
			//finally!
		} finally {
			//just thank the user for using ZooTix - even if everything else goes to %$&@.
			System.out.println("\nThank you for using ZooTix!");
		}
		
		//close the scanner
		scanner.close();
	}

}
