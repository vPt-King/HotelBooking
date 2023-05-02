package HotelBooking.Entity;

import java.util.List;

public class RoomsRequest {
	private int id;
	private String name;
    private int area;
    private int price;
    private int quantity;
    private int adult;
    private int children;
    private String description;
    private List<Integer> features;
    private List<Integer> facilities;
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
	public List<Integer> getFeatures() {
		return features;
	}
	public void setFeatures(List<Integer> features) {
		this.features = features;
	}
	public List<Integer> getFacilities() {
		return facilities;
	}
	public void setFacilities(List<Integer> facilities) {
		this.facilities = facilities;
	}
	public RoomsRequest(String name, int area, int price, int quantity, int adult, int children, String description,
			List<Integer> features, List<Integer> facilities) {
		this.name = name;
		this.area = area;
		this.price = price;
		this.quantity = quantity;
		this.adult = adult;
		this.children = children;
		this.description = description;
		this.features = features;
		this.facilities = facilities;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RoomsRequest(int id, String name, int area, int price, int quantity, int adult, int children,
			String description, List<Integer> features, List<Integer> facilities) {
		this.id = id;
		this.name = name;
		this.area = area;
		this.price = price;
		this.quantity = quantity;
		this.adult = adult;
		this.children = children;
		this.description = description;
		this.features = features;
		this.facilities = facilities;
	}
	public RoomsRequest() {
	}
	
    
}
