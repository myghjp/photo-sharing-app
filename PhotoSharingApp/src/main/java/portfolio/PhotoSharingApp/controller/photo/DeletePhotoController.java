package portfolio.PhotoSharingApp.controller.photo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeletePhotoController {
	
	@GetMapping("/delete-photo")
	public String getDeletePhoto() {
		
		return "photo/delete-photo";
	}

}
