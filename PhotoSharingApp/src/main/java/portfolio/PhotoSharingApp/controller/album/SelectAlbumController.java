package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SelectAlbumController {
	
	@GetMapping("/select-album")
	public String getSelectAlbum() {
		
		return "album/select-album";
	}
	
	@PostMapping("/select-album")
	public String postSelectAlbum() {
		return "redirect:select-album";
	}

}
