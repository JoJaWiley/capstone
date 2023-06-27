package zootix;

public class TicketFactory {
	//create single object of TicketFactory class
	private static TicketFactory instance = new TicketFactory();
	
	//make the constructor private
	private TicketFactory() {
		
	}
	
	//get the only object available
	public static TicketFactory getInstance() {
		return instance;
	}
	
	//get instances for various ticket types
public Ticket getTicket(String ticketType) {
	if (ticketType == null) 
		return null;
	if (ticketType.equalsIgnoreCase("c"))
		return new Child();
	else if (ticketType.equalsIgnoreCase("r"))
		return new Regular();
	else if (ticketType.equalsIgnoreCase("s"))
		return new Senior();
	return null;
}
}
