package portfolio.PhotoSharingApp.controller.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.form.album.DeleteAlbumForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
public class DeleteAlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/delete-album/{id}")
	public String getDeleteAlbum(Model model
			,@PathVariable("id")int albumId
			,@AuthenticationPrincipal LoginUserDetails user
			,DeleteAlbumForm form
			,RedirectAttributes redirectAttributes
			) {
		
		Album album = new Album();
		album.setId(albumId);
		
		/*自身が作成したアルバムであるかを確認*/
		if (albumService.isAlbum(album.getId(),user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		/*アルバムIDとアルバム名を取得*/
		Album albumData = albumService.findById(album.getId());
		
		model.addAttribute("albumData", albumData);
		model.addAttribute("deleteAlbumForm",form);
		
		return "album/delete-album";
	}
	
	@PostMapping("/delete-album")
	public String postDeleteAlbum(
			@RequestParam("id")int albumId
			,RedirectAttributes redirectAttributes
			) {
		
		albumService.delete(albumId);
		
		return "redirect:select-album";
	}
}