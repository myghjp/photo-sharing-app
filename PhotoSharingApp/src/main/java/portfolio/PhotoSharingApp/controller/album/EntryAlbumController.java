package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryAlbumController {
	
	@GetMapping("/entry-album")
	public String getEntryAlbum() {
		
		return "album/entry-album";
	}

}
