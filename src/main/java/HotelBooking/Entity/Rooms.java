package HotelBooking.Entity;

public class Rooms {
	private int id;
	private String name;
	private int area;
	private int price;
	private int quantity;
	private int adult;
	private int children;
	private String description;
	private int status;
	public Rooms() {
	}
	public Rooms(int id, String name, int area, int price, int quantity, int adult, int children, String description) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.price = price;
		this.quantity = quantity;
		this.adult = adult;
		this.children = children;
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getAdult() {
		return adult;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Rooms(int id, String name, int area, int price, int quantity, int adult, int children, String description,
			int status) {
		this.id = id;
		this.name = name;
		this.area = area;
		this.price = price;
		this.quantity = quantity;
		this.adult = adult;
		this.children = children;
		this.description = description;
		this.status = status;
	}
	public Rooms(String name, int area, int price, int quantity, int adult, int children, String description) {
		this.name = name;
		this.area = area;
		this.price = price;
		this.quantity = quantity;
		this.adult = adult;
		this.children = children;
		this.description = description;
		
	}
	
}
