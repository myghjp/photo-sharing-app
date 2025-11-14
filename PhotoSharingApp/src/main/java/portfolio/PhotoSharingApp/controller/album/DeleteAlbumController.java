package portfolio.PhotoSharingApp.controller.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.form.album.DeleteAlbumForm;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
public class DeleteAlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/delete-album/{id}")
	public String getDeleteAlbum(Model model
			,@PathVariable("id")int id
			,DeleteAlbumForm deleteAlbumForm
			) {
		
		model.addAttribute("id",id);
		model.addAttribute("deleteAlbumForm",deleteAlbumForm);
		
		return "album/delete-album";
	}
	
	@PostMapping("/delete-album")
	public String postDeleteAlbum(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		/*アルバムの削除*/
		albumService.deleteAlbum(id);
		
		return "redirect:select-album";
	}
}
