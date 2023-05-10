package HotelBooking.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import HotelBooking.Entity.Room_Feature_Facilities;
import HotelBooking.Entity.Rooms;
import HotelBooking.Dao.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/rooms")
public class Client {
	
	
	@GetMapping("/get-room-feature-and-facilities")
	public List<Room_Feature_Facilities> GetAllRoomDetails()
	{
		Room_Feature_FacilitiesDAO rffDAO= new Room_Feature_FacilitiesDAO();
		return rffDAO.GetAllRoomInformation();
	}
	
	@PostMapping("/get-room-feature-and-facilities-detail")
	public Room_Feature_Facilities GetRoomDetail(@RequestBody Rooms room)
	{
		Room_Feature_Facilities rff = null;
		Room_Feature_FacilitiesDAO rffDAO= new Room_Feature_FacilitiesDAO();
		List<Room_Feature_Facilities> list = rffDAO.GetAllRoomInformation();
		for(Room_Feature_Facilities rff1:list)
		{
			if(rff1.getRoom().getId() == room.getId())
			{
				rff = rff1;
			}
		}
		return rff;
	}
}
