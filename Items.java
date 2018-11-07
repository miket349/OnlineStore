package OnlineStore;

public class Items {
	int id;
	String name;
	String description;
	int quantity;
	double price;
	double total;
	boolean cart;
	int orderNum;
	
	public Items(int id, String name, String description, int quantity, double price, double total, boolean cart, int orderNum) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.cart = cart;
		this.orderNum = orderNum;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getTotal() {
		return total;
	}

	public boolean isCart() {
		return cart;
	}

	public void setCart(boolean cart) {
		this.cart = cart;
	}

	public int getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
}