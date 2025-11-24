package portfolio.PhotoSharingApp.controller.photo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ListPhotoController {
	
	@GetMapping("/list-photo")
	public String getListPhoto(RedirectAttributes redirectAttributes
			) {
		
	
		
		return "photo/list-photo";
	}

}
