package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import portfolio.PhotoSharingApp.entity.Groups;

@Controller
@SessionAttributes(value = {"groups"})
public class SelectAlbumController {
	
	@GetMapping("/select-album")
	public String getSelectAlbum(Model model
			,Groups groups) {
		
		model.addAttribute("groupName",groups.getGroupName());
		return "album/select-album";
	}

}
