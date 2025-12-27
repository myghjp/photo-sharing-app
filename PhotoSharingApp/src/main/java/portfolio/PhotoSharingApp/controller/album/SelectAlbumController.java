package portfolio.PhotoSharingApp.controller.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"groups","albums"})
public class SelectAlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@ModelAttribute(value = "albums")
	public Albums albums() {
		return new Albums();
	}
	
	@GetMapping("/select-album")
	public String getSelectAlbum(Model model
			,HttpSession httpSession
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("groups")Groups groups
			,RedirectAttributes redirectAttributes
			) {
		
		httpSession.removeAttribute("albums");
		
		/*model.addAttribute("groupName",groups.getGroupName());*/
		
		/*このグループのアルバムIDとアルバム名を取得*/
		List<Albums> albumsList = albumService.getAlbumList(groups.getId());
		model.addAttribute("albumsList", albumsList);
		
		/*一致すると自身は管理者*/
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		return "album/select-album";
	}
	
	@PostMapping("/select-album")
	public String postSelectAlbum(Model model
			,@RequestParam("id")int albumId
			,RedirectAttributes redirectAttributes
			) {
		
		/*↓Modelでset変更では*/
		
		/*アルバムIDとアルバム名を取得*/
		Albums albums = albumService.getAlbum(albumId);
		/*albums.setId(albums.getId());
		albums.setAlbumName(albums.getAlbumName());*/
		redirectAttributes.addFlashAttribute("albums",albums);

		return "redirect:list-photo";
	}
}