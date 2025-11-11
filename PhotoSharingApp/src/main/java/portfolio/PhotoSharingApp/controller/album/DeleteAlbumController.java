package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteAlbumController {
	
	@GetMapping("/delete-album")
	public String getDeleteAlbum() {
		
		return "album/delete-album";
	}

}
