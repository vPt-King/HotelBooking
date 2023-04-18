package HotelBooking;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import DAO.Admin_credDao;
import DAO.Contact_detailsDAO;
import DAO.SettingsDAO;
import DAO.Team_detailsDAO;
import DB.DBconnect;
import ValidateUser.UserCookie;
import ValidateUser.Validate;
import Model.Admin_cred;
import Model.Contact_details;
import Model.Settings;
import Model.Team_details;
import Model.User;
@Controller
@RequestMapping("/admin")
public class Admin {
	
	//---------login and logout admin-----------------
	@GetMapping("/login")
	public String home(HttpServletRequest request,HttpServletResponse response)
	{
		if(Validate.isAdmin(request, response)) {
		return "redirect:/admin/dashboard";
		}
		return "Admin";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response, @RequestParam("admin_name") String name,@RequestParam("admin_pass") String pass, Model model) throws SQLException
	{
		model.addAttribute("name", name);
		model.addAttribute("pass",pass);
		Admin_cred admin = Admin_credDao.GetAdminByName(name);
		if(admin == null)
		{
			model.addAttribute("mess", "User doesn't existed");
			return "Admin";
		}
		else {
			if(pass.equals(admin.getAdmin_pass()))
			{
				UserCookie.SetAdminCookie(request, response, admin);
				return "redirect:/admin/dashboard";
			}
			else {
				model.addAttribute("mess", "password is wrong");
				return "Admin";
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("isAdmin")) { //check if cookie exists by name
                    cookie.setMaxAge(0); //delete cookie by setting max age to 0
                    response.addCookie(cookie); //add updated cookie to response
                    break;
                }
            }
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/admin/login";
	}
	
	
	// --------------------dashboard admin-------------------------
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest request,HttpServletResponse response)
	{
		if(Validate.isAdmin(request, response)) {
		return "Admin_DashBoard";
		}
		return "redirect:/admin/login";
	}
	
	
	
	//-------------------Setting admin ----------------------------
	@GetMapping("/settings")
	public String settings(HttpServletRequest request,HttpServletResponse response)
	{
		if(Validate.isAdmin(request, response)) {
			return "Admin_Settings";
		}
		
		return "redirect:/admin/login";
	}
	@PostMapping("/settings_crud/management_team")
	public ResponseEntity<String> handleManagementRequest(MultipartHttpServletRequest request) throws IOException {
	    String name = request.getParameter("name");
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
	    team_detailsDAO.Insert_Team_details(name, photoName);
	    return ResponseEntity.ok("1");
	}
	@PostMapping("/settings_crud")
	public ResponseEntity<String> handleSettingsCrudRequest(HttpServletRequest request) throws IOException {
		
		String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		String[] params = requestBody.split("&");
		int n = params.length;
		ObjectMapper objectMapper = new ObjectMapper();
		Team_detailsDAO team_detailsDAO = new Team_detailsDAO();
		// Xử lý yêu cầu POST tùy ý
	    if (params[n-1].equals("get_general")) {
	    	Settings settings = SettingsDAO.GetSettingsById(1);
	    	if(settings == null)
	    	{
	    		return ResponseEntity.ok("No entity!");
	    	}
	    	else {
	    		return ResponseEntity.ok(objectMapper.writeValueAsString(settings));
	    	}
	    } 
	    if(params[n-1].equals("upd_general")) 
	    {
	    	String site_title = params[0];
	    	String site_about = params[1];
	    	SettingsDAO.Update_Settings_SiteTitle_SiteAbout_ById(1,site_title, site_about);
	    	return ResponseEntity.ok("1");
	    }
	    if(params[n-1].equals("upd_shutdown"))
	    {
	    
	    	int shutdown = Integer.parseInt(params[0]);
	    	if(shutdown == 1)
	    	{
	    		shutdown = 0;
	    	}
	    	else {
	    		shutdown = 1;
	    	}
	    	SettingsDAO.Update_Settings_Shutdown_ById(1, shutdown);
	    	return ResponseEntity.ok("1");
	    }
	    if(params[n-1].equals("get_contacts"))
	    {
	    	Contact_detailsDAO cdDAO = new Contact_detailsDAO();
	    	Contact_details cd = cdDAO.Get_Contact_details_ById(1);
	    	return ResponseEntity.ok(objectMapper.writeValueAsString(cd));
	    	
	    }
	    if(params[n-1].equals("upd_contacts"))
	    {
	    	
	    	String address = params[0];
	    	String gmap = params[1];
	    	String pn1 = params[2];
	    	String pn2 = params[3];
	    	String email = params[4];
	    	String fb = params[5];
	    	String insta = params[6];
	    	String tw = params[7];
	    	String iframe = params[8];
	    	Contact_detailsDAO cdDAO = new Contact_detailsDAO();
	    	int update = cdDAO.Update_Contact_Details_ById(1, address, gmap, pn1, pn2, email, fb, insta, tw, iframe);
	    	if(update == 1)
	    	{
	    	return ResponseEntity.ok("1");
	    	}else {
	    	return ResponseEntity.badRequest().body("Invalid request!");
	    	}
	    }
	    if(params[n-1].equals("get_members"))
	    {
	    	List<Team_details> list = team_detailsDAO.Get_All_Members();
	    	String json = objectMapper.writeValueAsString(list);
	    	return ResponseEntity.ok(json);
	    }
	    if(params[n-1].equals("rem_member"))
	    {
	    	int id = Integer.parseInt(params[0]);
	    	team_detailsDAO.Delete_Team_Details(id);
	    	return ResponseEntity.ok("1");
	    }
	    else {
	        // Nếu không phải "get_general", trả về phản hồi lỗi
	        return ResponseEntity.badRequest().body("Invalid request!");
	    }
    }
	
	
}
