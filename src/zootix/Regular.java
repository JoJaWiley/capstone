package zootix;

//regular price ticket class
public class Regular implements Ticket {
	private String type = "Regular";
	private double price;
	
	@Override
	public double getPrice() {
		return price;
	}
	
	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String getType() {
		return type;
	}
}
