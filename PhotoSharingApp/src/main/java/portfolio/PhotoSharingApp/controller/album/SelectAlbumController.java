package portfolio.PhotoSharingApp.controller.album;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SelectAlbumController {
	
	@GetMapping("/select-album")
	public String getSelectAlbum(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "album/select-album";
	}
	
	@PostMapping("/select-album")
	public String postSelectAlbum(@RequestParam("id")int id
				,RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("id",id);
		
		return "redirect:select-album";
	}

}
