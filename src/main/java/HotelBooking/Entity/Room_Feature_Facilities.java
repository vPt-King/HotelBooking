package HotelBooking.Entity;

import java.util.List;

public class Room_Feature_Facilities {
	private Rooms room;
	private List<Facilities> listFacilities;
	private List<Features> listFeatures;
	private Room_images room_images;
	private List<Room_images> listRoom_images;

	public Rooms getRoom() {
		return room;
	}
	public void setRoom(Rooms room) {
		this.room = room;
	}
	public List<Facilities> getListFacilities() {
		return listFacilities;
	}
	public void setListFacilities(List<Facilities> listFacilities) {
		this.listFacilities = listFacilities;
	}
	public List<Features> getListFeatures() {
		return listFeatures;
	}
	public void setListFeatures(List<Features> listFeatures) {
		this.listFeatures = listFeatures;
	}
	public Room_images getRoom_images() {
		return room_images;
	}
	public void setRoom_images(Room_images room_images) {
		this.room_images = room_images;
	}

	public Room_Feature_Facilities(Rooms room, List<Facilities> listFacilities, List<Features> listFeatures,
			Room_images room_images, List<Room_images> listRoom_images) {
		this.room = room;
		this.listFacilities = listFacilities;
		this.listFeatures = listFeatures;
		this.room_images = room_images;
		this.listRoom_images = listRoom_images;
	}
	public List<Room_images> getListRoom_images() {
		return listRoom_images;
	}
	public void setListRoom_images(List<Room_images> listRoom_images) {
		this.listRoom_images = listRoom_images;
	}

	
	
	
}
