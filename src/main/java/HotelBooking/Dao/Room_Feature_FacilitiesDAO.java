package HotelBooking.Dao;

import java.util.List;
import java.util.ArrayList;

import HotelBooking.Entity.Facilities;
import HotelBooking.Entity.Features;
import HotelBooking.Entity.Room_Feature_Facilities;
import HotelBooking.Entity.Room_images;
import HotelBooking.Entity.Rooms;

public class Room_Feature_FacilitiesDAO {
	public List<Room_Feature_Facilities> GetAllRoomInformation()
	{
		List<Room_Feature_Facilities> list = new ArrayList<>();
		RoomsDAO roomsDAO = new RoomsDAO();
		List<Rooms> listRooms = roomsDAO.GetAllRooms(); 
		for(Rooms rooms:listRooms)
		{
			FeaturesDAO featuresDAO = new FeaturesDAO();
			List<Features> listFeature = featuresDAO.GetListFeaturesByRoomId(rooms.getId());
			FacilitiesDAO facilitiesDAO = new FacilitiesDAO();
			List<Facilities> listFacilities = facilitiesDAO.GetListFacilitiesByRoomId(rooms.getId());
			Room_imageDAO room_imageDAO = new Room_imageDAO();
			Room_images room_images = room_imageDAO.GetRoom_imagesThumbedByRoom_id(rooms.getId());
			List<Room_images> listRoom_images = room_imageDAO.GetRoomImagesById(rooms.getId());
			list.add(new Room_Feature_Facilities(rooms, listFacilities, listFeature, room_images,listRoom_images));
		}
		return list;
	}
}
