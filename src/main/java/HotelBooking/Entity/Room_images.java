package HotelBooking.Entity;

public class Room_images {
	private int id;
	private int room_id;
	private String image;
	private int thumb;
	public int getThumb() {
		return thumb;
	}
	public void setThumb(int thumb) {
		this.thumb = thumb;
	}
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Room_images(int id, int room_id, String image, int thumb) {
		this.id = id;
		this.room_id = room_id;
		this.image = image;
		this.thumb = thumb;
	}
	
	
}
