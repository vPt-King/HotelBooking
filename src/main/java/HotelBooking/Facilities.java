package HotelBooking;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Facilities {
	
	@GetMapping("/facilities")
	public String home()
	{
		return "Facilities";
	}
}
