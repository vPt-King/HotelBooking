package HotelBooking;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Rooms {
	@GetMapping("/rooms")
	public String home()
	{
		return "Rooms";
	}
}
