package HotelBooking.Entity;

public class Room_Facilities {
	private int id;
	private int room_id;
	private int facilities_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getFacilities_id() {
		return facilities_id;
	}
	public void setFacilities_id(int facilities_id) {
		this.facilities_id = facilities_id;
	}
	public Room_Facilities(int id, int room_id, int facilities_id) {
		this.id = id;
		this.room_id = room_id;
		this.facilities_id = facilities_id;
	}
	
}
