package zootix;

//Senior ticket class with discount built into setPrice method
public class Senior implements Ticket {
	private String type = "Senior";
	private double price;

	@Override
	public double getPrice() {
		return price;
}

	@Override
	public void setPrice(double price) {
		this.price = price - 1;
}

	@Override
	public String getType() {
		return type;
	}
}
