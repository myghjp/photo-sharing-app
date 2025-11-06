package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SelectAlbumController {
	
	@GetMapping("/select-album")
	public String getSelectAlbum() {
		
		return "album/select-album";
	}

}
