package zootix;

//Child ticket class with discount built into setPrice method
public class Child implements Ticket {
	private String type = "Child";
	private double price;
	
	@Override
	public double getPrice() {
		return price;
	}
	
	@Override
	public void setPrice(double price) {
		this.price = price - 2;
	}

	@Override
	public String getType() {
		return type;
	}
}
