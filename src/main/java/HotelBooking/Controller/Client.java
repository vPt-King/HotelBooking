package HotelBooking.Controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import HotelBooking.Entity.Booking;
import HotelBooking.Entity.Message;
import HotelBooking.Entity.Room_Feature_Facilities;
import HotelBooking.Entity.Rooms;
import HotelBooking.Entity.User_cred;
import HotelBooking.Dao.*;
import com.google.gson.Gson;
@RestController
@CrossOrigin(origins = {"http://localhost:3000/rooms",
		"http://localhost:3000/register",
		"http://localhost:3000/"
		})
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
	
	@PostMapping("/register")
	public ResponseEntity<String> UserRegister(MultipartHttpServletRequest request)
	{
		System.out.println("here");
		Gson gson = new Gson();
		MultipartFile photo = request.getFile("profile");
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String phone = request.getParameter("phone");
	    String identification = request.getParameter("identification");
	    String dob = request.getParameter("dob");
	    String password = request.getParameter("pass");
	    String address = request.getParameter("address");
	    
	    String photoName = photo.getOriginalFilename();
	    Message mes = null;
	    Path path = Paths.get("uploads/");
	    try {
	    InputStream is = photo.getInputStream();
	    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    }catch(Exception e)
	    {
	    	System.out.println("no");
	    }
	    User_cred user = new User_cred(name,email,phone,identification,dob,photoName,password,address);
	    System.out.println(user.getEmail() + " " + user.getPassword());
	    User_credDAO uDAO = new User_credDAO();
	    int check = uDAO.CheckIfIdentificationIsExist(user.getIdentification());
	    if(check == 1)
	    {
	    	Message mess = new Message(0,"Identification is existed");
	    	String json = gson.toJson(mess);
	    	return new ResponseEntity<String>(json,HttpStatus.OK);
	    	
	    }
	    int k = uDAO.InsertNewUser(user);
	    if(k == 0)
	    {
	    	Message mess = new Message(0,"Can not register");
	    	String json = gson.toJson(mess);
	    	return new ResponseEntity<String>(json,HttpStatus.OK);
	    }
	    else {
	    	Message mess = new Message(1,"Register successfully");
	    	String json = gson.toJson(mess);
	    	return new ResponseEntity<String>(json,HttpStatus.OK);
	    }
	}
	@PostMapping("/login")
	public ResponseEntity<String> UserLogin(@RequestBody User_cred user)
	{
		User_credDAO uDAO = new User_credDAO();
		int k = uDAO.CheckIfUserIsExisted(user);
		Gson gson = new Gson();
		Message mess = null;
		if(k == 0)
		{
	    	mess = new Message(0, "Login failure!! Check your account again");
	    }
	    else {
	    	mess = new Message(1, "Login success");
	    }
		String json = gson.toJson(mess);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}

	@PostMapping("/get-user-by-gmail")
	public ResponseEntity<String> UserConfirmed(@RequestBody User_cred user)
	{
		User_credDAO uDAO = new User_credDAO();
		Gson gson = new Gson();
		User_cred u = uDAO.GetUserByEmail(user.getEmail());
		String json = gson.toJson(u);
		System.out.println(json);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@PostMapping("/booking-room")
	public ResponseEntity<String> BookingRoom(@RequestBody Booking booking)
	{
		Message mess = null;
		Gson gson = new Gson();
		BookingDAO bDAO = new BookingDAO();
		int k = bDAO.InsertNewBooking(booking);
		if(k == 0)
		{
			mess = new Message(0,"Can not book room");
		}
		else {
			RoomsDAO rDAO = new RoomsDAO();
			rDAO.ChangeStatusById(booking.getRoom_id(), 0);
			mess = new Message(0,"Booking successfully");
		}
		String json = gson.toJson(mess);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
}
