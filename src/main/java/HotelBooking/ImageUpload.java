package HotelBooking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class ImageUpload {
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
}
