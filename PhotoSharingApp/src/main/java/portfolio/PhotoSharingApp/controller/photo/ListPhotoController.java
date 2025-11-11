package portfolio.PhotoSharingApp.controller.photo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListPhotoController {
	
	@GetMapping("/list-photo")
	public String getListPhoto() {
		
		return "photo/list-photo";
	}

}
