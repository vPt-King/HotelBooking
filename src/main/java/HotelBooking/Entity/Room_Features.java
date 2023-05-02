package HotelBooking.Entity;

public class Room_Features {
	private int id;
	private int room_id;
	private int features_id;
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
	public int getFeatures_id() {
		return features_id;
	}
	public void setFeatures_id(int features_id) {
		this.features_id = features_id;
	}
	public Room_Features(int id, int room_id, int features_id) {
		this.id = id;
		this.room_id = room_id;
		this.features_id = features_id;
	}
	
}
