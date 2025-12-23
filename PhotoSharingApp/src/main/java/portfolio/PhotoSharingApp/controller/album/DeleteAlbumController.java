package portfolio.PhotoSharingApp.controller.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Albums;
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
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,DeleteAlbumForm deleteAlbumForm
			,RedirectAttributes redirectAttributes
			) {
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*※このアルバムidが自身が作成したのかを確認*/
		Albums albums = new Albums();
		albums.setId(albumId);
		
		if (albumService.isCurrentUser(albums.getId()) != loginUserDetails.getUserId()) {
			throw new IllegalArgumentException("不正なIDです");
		}
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		
		/*アルバムIDとアルバム名を取得*/
		Albums albumsData = albumService.getAlbum(albums.getId());
		
		model.addAttribute("albumsData", albumsData);
		model.addAttribute("deleteAlbumForm",deleteAlbumForm);
		
		return "album/delete-album";
	}
	
	@PostMapping("/delete-album")
	public String postDeleteAlbum(
			@RequestParam("id")int albumId
			,RedirectAttributes redirectAttributes
			) {
		
		albumService.deleteAlbum(albumId);
		
		return "redirect:select-album";
	}
}