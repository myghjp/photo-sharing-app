package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.form.album.EntryAlbumForm;

@Controller
@SessionAttributes(value = {"groups"})
public class EntryAlbumController {
	
	@GetMapping("/entry-album")
	public String getEntryAlbum(Model model
			,EntryAlbumForm entryAlbumForm) {
		
		return "album/entry-album";
	}
	
	@PostMapping("/entry-album")
	public String postEntryAlbum(Model model
			,Groups groups
			,@RequestParam("albumName")String albumName) {
		
		
		
		
		/*グループIDとアルバム名を登録する*/
		
		
		
		return "album/entry-album";
	}
	
	

}
