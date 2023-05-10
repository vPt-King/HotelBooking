package HotelBooking.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import HotelBooking.Dao.Admin_credDao;
import HotelBooking.Dao.RoomsDAO;
import HotelBooking.Dao.Room_FacilitiesDAO;
import HotelBooking.Dao.Room_FeaturesDAO;
import HotelBooking.Entity.Admin_cred;
import HotelBooking.Entity.Carousel;
import HotelBooking.Entity.Settings;
import HotelBooking.Entity.Contact_details;
import HotelBooking.Entity.Features;
import HotelBooking.Entity.RoomsRequest;
import HotelBooking.Entity.Facilities;
import HotelBooking.Entity.Team_details;
import HotelBooking.Entity.User_queries;
import HotelBooking.Entity.Rooms;
import HotelBooking.Entity.RoomResponse;
import HotelBooking.Entity.Room_Facilities;
import HotelBooking.Entity.Room_Feature_Facilities;
import HotelBooking.Entity.Room_images;
import HotelBooking.Dao.SettingsDAO;
import HotelBooking.Dao.Contact_detailsDAO;
import HotelBooking.Dao.Team_detailsDAO;
import HotelBooking.Dao.CarouselDAO;
import HotelBooking.Dao.User_queriesDAO;
import HotelBooking.Dao.FeaturesDAO;
import HotelBooking.Dao.FacilitiesDAO;
import HotelBooking.Dao.Room_imageDAO;
import Model.LoginResponse;
import Model.MessResponse;
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000/admin/login")

public class Admin {	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Admin_cred model){
		Admin_credDao admin_credDao = new Admin_credDao();
		Admin_cred admin = admin_credDao.FindByAdmin_nameAndAdmin_pass(model.getAdmin_name(),model.getAdmin_pass());
	    if(admin != null) {
	        LoginResponse response = new LoginResponse(1, "Logic success");
	        Gson gson = new Gson();
	        String json = gson.toJson(response);
	        return new ResponseEntity<String>(json, HttpStatus.OK);
	    }
	    LoginResponse response = new LoginResponse(0, "Login Failure! Please check your username or password");
	    Gson gson = new Gson();
	    String json = gson.toJson(response);
	    return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@GetMapping("/settings/general")
	public Settings GetGeneral()
	{
		SettingsDAO settingsDAO = new SettingsDAO();
		Settings settings = settingsDAO.GetSettingsById(1);
		return settings;
	}
	
	@PostMapping("/settings/general")
	public void UpdateGeneral(@RequestBody Settings settings)
	{
		SettingsDAO settingsDAO = new SettingsDAO();
		settingsDAO.UpdateSettings(settings);
	}
	
	@GetMapping("/settings/contact")
	public Contact_details GetContact_details()
	{
		Contact_detailsDAO contact_detailsDAO = new Contact_detailsDAO();
		Contact_details contact_details = contact_detailsDAO.GetContact_detailsById(1);
		return contact_details;
	}
	
	@PostMapping("/settings/contact")
	public void UpdateContact_details(@RequestBody Contact_details contact_details)
	{
		Contact_detailsDAO contact_detailsDAO = new Contact_detailsDAO();
		contact_detailsDAO.UpdateContact_details(contact_details);
	}
	
	
	@GetMapping("/settings/management_team")
	public List<Team_details> GetTeamDetails()
	{
		Team_detailsDAO team_detailsDAO = new Team_detailsDAO();
		return team_detailsDAO.GetAllTeam_detailsMember();
	}
	
	
	@PostMapping("/settings/management_team")
	public ResponseEntity<String> handleManagementRequest(MultipartHttpServletRequest request) throws IOException {
	    String name = request.getParameter("name");
//	    String description = request.getParameter("description");
//	    int quantity = Integer.parseInt(request.getParameter("quantity"));
//	    double price = Double.parseDouble(request.getParameter("price"));
//	    int category_id = Integer.parseInt(request.getParameter("category_id"));
//	    
	    MultipartFile photo = request.getFile("picture");
	    String photoName = photo.getOriginalFilename();
	    Path path = Paths.get("uploads/");
	    try {
	    InputStream is = photo.getInputStream();
	    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    }catch(Exception e)
	    {
	    	System.out.println("no");
	    }
	    Team_detailsDAO team_detailsDAO = new Team_detailsDAO();
	    team_detailsDAO.InsertTeamDetails(name, photoName);
	    return ResponseEntity.ok("1");
	}
	
	@RequestMapping(value = "/getimage/{photo}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo)
	{
		if(!photo.equals("")||photo != null)
		{
			try {
				Path filename = Paths.get("uploads",photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource bar = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(bar);
			}catch(Exception e)
			{
				System.err.println("Upload err");
			}
		}
		return ResponseEntity.badRequest().build();
	}

	
	@DeleteMapping("/settings/management_team")
	public void DeleteTeam_details(@RequestBody int id)
	{
		Team_detailsDAO team_detailsDAO = new Team_detailsDAO();
		team_detailsDAO.DeleteTeam_detailsById(id);
	}
	
	@PostMapping("/carousel")
	public ResponseEntity<String> InsertNewCarousel(MultipartHttpServletRequest request)
	{
		MultipartFile photo = request.getFile("picture");
		String photoName = photo.getOriginalFilename();
		Path path = Paths.get("uploads/");
		try {
		    InputStream is = photo.getInputStream();
		    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		    }
		catch(Exception e)
		    {
		    	System.out.println("no");
		    }
		CarouselDAO carouselDAO = new CarouselDAO();
		int k = carouselDAO.InsertNewCarousel(new Carousel(photoName));
		return ResponseEntity.ok("1");
	}
	
	@GetMapping("/carousel")
	public List<Carousel> GetListCarousel()
	{
		CarouselDAO carouselDAO = new CarouselDAO();
		return carouselDAO.FindAllCarousel();
	}
	
	@DeleteMapping("/carousel")
	public void DeleteCarousel(@RequestBody Carousel carousel)
	{
		CarouselDAO carouselDAO = new CarouselDAO();
		carouselDAO.DeleteCarouselById(carousel);
	}
	
	
	@PostMapping("/user-queries")
	public ResponseEntity<String> InsertNewUser_queries(@RequestBody User_queries uq)
	{
		Gson gson = new Gson();
		User_queriesDAO uqDAO = new User_queriesDAO();
		int k = uqDAO.InsertNewUser_queries(uq);
		MessResponse mr = null;
		if(k != 1)
		{
			mr = new MessResponse(0,"Error occure !! Cannot send email");
		}
		else {
			mr = new MessResponse(1,"Send email ok");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@GetMapping("/user-queries")
	public List<User_queries> GetListUser_queries()
	{
		User_queriesDAO uqDAO = new User_queriesDAO();
		return uqDAO.GetAllUser_queries();
	}
	
	@PutMapping("/user-queries")
	public ResponseEntity<String> MarkAsRead(@RequestBody User_queries uq)
	{
		Gson gson = new Gson();
		User_queriesDAO uqDAO = new User_queriesDAO();
		int k = uqDAO.ChangeSeenStatus(uq);
		MessResponse mr = null;
		if(k != 1)
		{
			mr = new MessResponse(0,"Cannot mark as read");
		}
		else {
			mr = new MessResponse(1,"Mark as read");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@DeleteMapping("/user-queries")
	public ResponseEntity<String> DeleteUser_queries(@RequestBody User_queries uq)
	{
		Gson gson = new Gson();
		User_queriesDAO uqDAO = new User_queriesDAO();
		int k = uqDAO.DeleteUser_queries(uq);
		MessResponse mr = null;
		if(k != 1)
		{
			mr = new MessResponse(0,"Cannot delete");
		}
		else {
			mr = new MessResponse(1,"delete success");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@PutMapping("/user-queries/all")
	public ResponseEntity<String> MarkAsReadAll()
	{
		Gson gson = new Gson();
		User_queriesDAO uqDAO = new User_queriesDAO();
		int k = uqDAO.ChangeAllSeenStatus();
		MessResponse mr = null;
		if(k != 1)
		{
			mr = new MessResponse(0,"Cannot mark all as read");
		}
		else {
			mr = new MessResponse(1,"Mark all as read");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@DeleteMapping("/user-queries/all")
	public ResponseEntity<String> DeleteAllUser_queries()
	{
		Gson gson = new Gson();
		User_queriesDAO uqDAO = new User_queriesDAO();
		int k = uqDAO.DeleteAllUser_queries();
		MessResponse mr = null;
		if(k != 1)
		{
			mr = new MessResponse(0,"Cannot delete all");
		}
		else {
			mr = new MessResponse(1,"delete all success");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@PostMapping("/features_facilities/features")
	public ResponseEntity<String> InsertFeatures(@RequestBody Features uq)
	{
		Gson gson = new Gson();
		FeaturesDAO fDAO = new FeaturesDAO();
		int k = fDAO.InsertFeatures(uq);
		MessResponse mr = null;
		if(k != 1)
		{
			mr = new MessResponse(0,"Error");
		}
		else {
			mr = new MessResponse(1,"Success");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}	
	
	@GetMapping("/features_facilities/features")
	public List<Features> GetFeatures()
	{
		FeaturesDAO featuresDAO = new FeaturesDAO();
		return featuresDAO.GetFeatures();
	}
	
	@DeleteMapping("/features_facilities/features")
	public ResponseEntity<String> DeleteFeturesById(@RequestBody Features qq)
	{
		Gson gson = new Gson();
		MessResponse mr = null;
		FeaturesDAO featuresDAO = new FeaturesDAO();
		if(featuresDAO.CheckIfUseFeature(qq) != 0)
		{
			mr = new MessResponse(1, "Remove feature successfully");
		   featuresDAO.DeleteFeturesById(qq);
		}
		else {
			mr = new MessResponse(0, "Remove feature not successfully");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@GetMapping("/features_facilities/facility")
	public List<Facilities> GetFacilities()
	{
		FacilitiesDAO facilitiesDAO = new FacilitiesDAO();
		return facilitiesDAO.GetFacilities();
	}
	
	
	@PostMapping("/features_facilities/facility")
	public ResponseEntity<String> FacilitiesRequest(MultipartHttpServletRequest request) throws IOException {
	    MultipartFile photo = request.getFile("icon");
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    
	    String photoName = photo.getOriginalFilename();
	    Path path = Paths.get("uploads/");
	    try {
	    InputStream is = photo.getInputStream();
	    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    }catch(Exception e)
	    {
	    	System.out.println("no");
	    }
	    FacilitiesDAO facilitiesDAO = new FacilitiesDAO();
	    facilitiesDAO.InsertFacilities(photoName, name, description);
	    return ResponseEntity.ok("1");
	}
	

	
	@DeleteMapping("/features_facilities/facility")
	public void DeleteFacilities(@RequestBody Facilities qq)
	{
		Gson gson = new Gson();
		FacilitiesDAO facilitiesDAO = new FacilitiesDAO();
		
		facilitiesDAO.DeleteFacilities(qq);
	}
	
	@PostMapping("/rooms")
	public ResponseEntity<String> InsertNewRoom(@RequestBody RoomsRequest rr)
	{
		Gson gson = new Gson();
		MessResponse mr = new MessResponse(1,"insert room successfully");
		Rooms rooms = new Rooms(rr.getName(),rr.getArea(),rr.getPrice(),rr.getQuantity(),rr.getAdult(),rr.getChildren(),rr.getDescription());
		RoomsDAO roomsDAO = new RoomsDAO();
		roomsDAO.InsertNewRoom(rooms);
		int room_id = roomsDAO.FindRoom_idByName(rr.getName());
		Room_FacilitiesDAO rfDAO = new Room_FacilitiesDAO();
		List<Integer> facilities = rr.getFacilities();
		for(int i = 0; i < facilities.size();i++)
		{
			rfDAO.InsertNewRoom_Facilities(room_id,facilities.get(i));
		}
		Room_FeaturesDAO rfeDAO = new Room_FeaturesDAO();
		List<Integer> features = rr.getFeatures();
		for(int i = 0; i < features.size();i++)
		{
			rfeDAO.InsertNewRoom_Features(room_id,features.get(i));
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	
	@GetMapping("/get-all-rooms")
	public List<Rooms> GetAllRooms()
	{
		RoomsDAO roomsDAO = new RoomsDAO();
		List<Rooms> listRooms = roomsDAO.GetAllRooms();
		return listRooms;
	}
	@PostMapping("/change-status-room")
	public void ChangeStatusRoom(@RequestBody Rooms r)
	{
		RoomsDAO roomsDAO = new RoomsDAO();
		roomsDAO.ChangeStatusById(r);
	}
	
	@PostMapping("/get-room-detail")
	public RoomResponse GetRoomDetail(@RequestBody Rooms r)
	{
		RoomResponse rp = null;
		RoomsDAO roomsDAO = new RoomsDAO();
		Rooms room = roomsDAO.GetRoomById(r);
		FeaturesDAO featuresDAO = new FeaturesDAO();
		FacilitiesDAO facilitiesDAO = new FacilitiesDAO();
		List<Features> listFeature = featuresDAO.GetListFeaturesByRoomId(r.getId());
		List<Facilities> listFacilities = facilitiesDAO.GetListFacilitiesByRoomId(r.getId());
		rp = new RoomResponse(room.getName(), room.getArea(), room.getPrice(), room.getQuantity(), room.getAdult(), room.getChildren(), room.getDescription(), listFeature, listFacilities);
		return rp;
	}
	
	
	@PutMapping("/update-room")
	public ResponseEntity<String> UpdateRoom(@RequestBody RoomsRequest rr)
	{
		Gson gson = new Gson();
		int k = 1;
		MessResponse mr = null;
		Rooms rooms = new Rooms(rr.getId(),rr.getName(),rr.getArea(),rr.getPrice(),rr.getQuantity(),rr.getAdult(),rr.getChildren(),rr.getDescription());
		RoomsDAO roomsDAO = new RoomsDAO();
		k = roomsDAO.UpdateRoomById(rooms);
		int room_id = roomsDAO.FindRoom_idByName(rr.getName());
		Room_FacilitiesDAO rfDAO = new Room_FacilitiesDAO();
		rfDAO.DeleteOldOption(room_id);
		List<Integer> facilities = rr.getFacilities();
		for(int i = 0; i < facilities.size();i++)
		{
			rfDAO.InsertNewRoom_Facilities(room_id,facilities.get(i));
		}
		
		Room_FeaturesDAO rfeDAO = new Room_FeaturesDAO();
		rfeDAO.DeleteOldOption(room_id);
		List<Integer> features = rr.getFeatures();
		for(int i = 0; i < features.size();i++)
		{
			rfeDAO.InsertNewRoom_Features(room_id,features.get(i));
		}
		if(k != 1)
		{
			mr = new MessResponse(0,"Error");
		}
		else {
			mr = new MessResponse(1,"Success");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@PostMapping("/post-room-images")
	public ResponseEntity<String> PostRoomImage(MultipartHttpServletRequest request)
	{
		MultipartFile photo = request.getFile("picture");
		int room_id = Integer.parseInt(request.getParameter("room_id"));
		String photoName = photo.getOriginalFilename();
		Path path = Paths.get("uploads/");
		try 
		{
		    InputStream is = photo.getInputStream();
		    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		}
		catch(Exception e)
		{
		    System.out.println("no");
		}
		Gson gson = new Gson();
		MessResponse mr = null;
		Room_imageDAO riDAO = new Room_imageDAO();
		int k = riDAO.InsertNewImageRoom(room_id,photoName);
		if(k == 0) {
			mr = new MessResponse(0,"Insert not successfully");
		}
		else {
			mr = new MessResponse(1,"Insert successfully");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@PostMapping("/get-room-images")
	public List<Room_images> GetRoomImages(@RequestBody Room_images ri)
	{
		Room_imageDAO riDAO = new Room_imageDAO();
		return riDAO.GetRoomImagesById(ri.getId());
	}
	
	@DeleteMapping("/delete-room-images")
	public ResponseEntity<String> DeleteRoomImages(@RequestBody Room_images ri)
	{
		Gson gson = new Gson();
		MessResponse mr = null;
		Room_imageDAO riDAO = new Room_imageDAO();
		int k = riDAO.DeleteRoomImages(ri);
		if(k == 0)
		{
			mr = new MessResponse(0,"Delete not successfully");
		}
		else {
			mr = new MessResponse(0,"Delete successfully");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@PutMapping("/change-thumb-room-images")
	public ResponseEntity<String> ChangeThumbRoomImages(@RequestBody Room_images ri)
	{
		Gson gson = new Gson();
		MessResponse mr = null;
		Room_imageDAO riDAO = new Room_imageDAO();
		riDAO.SetOffThumbOfRoomImages(ri);
		int k = riDAO.ChangeThumbRoomImages(ri);
		if(k == 0)
		{
			mr = new MessResponse(0,"Change thumb not successfully");
		}
		else {
			mr = new MessResponse(1,"Change thumb successfully");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-room")
	public ResponseEntity<String> DeleteRoom(@RequestBody Rooms r)
	{
		Gson gson = new Gson();
		MessResponse mr = null;
		Room_imageDAO riDAO = new Room_imageDAO();
		riDAO.DeleteRoomImageByRoomId(r.getId());
		Room_FacilitiesDAO rfDAO = new Room_FacilitiesDAO();
		rfDAO.DeleteRoom_FacilitiesByRoomId(r.getId());
		Room_FeaturesDAO rfeDAO = new Room_FeaturesDAO();
		rfeDAO.DeleteRoom_FeaturesByRoomId(r.getId());
		RoomsDAO rDAO = new RoomsDAO();
		int k = rDAO.SetRemovedRoomById(r.getId());
		if(k == 0)
		{
			mr = new MessResponse(0,"Delete room is not successfully");
		}
		else {
			mr = new MessResponse(1,"Delete room successfully");
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	
	
}
