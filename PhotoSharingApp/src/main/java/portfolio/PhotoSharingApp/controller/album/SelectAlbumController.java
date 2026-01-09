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
import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"group","album"})
public class SelectAlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@ModelAttribute(value = "album")
	public Album album() {
		return new Album();
	}
	
	@GetMapping("/select-album")
	public String getSelectAlbum(Model model
			,HttpSession httpSession
			,@AuthenticationPrincipal LoginUserDetails user
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			) {
		
		httpSession.removeAttribute("album");
		
		/*このグループのアルバムIDとアルバム名を取得*/
		List<Album> albumList = albumService.getAlbumList(group.getId());
		model.addAttribute("albumList", albumList);
		
		/*一致すると自身は管理者*/
		if (group.getAccountId() == user.getAccountId()) {
			model.addAttribute("isAdmin",true);
		}
		
		return "album/select-album";
	}
	
	@PostMapping("/select-album")
	public String postSelectAlbum(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		/*アルバムIDとアルバム名を取得*/
		Album album = albumService.getAlbum(id);
		
		/*SessionAttributesに追加*/
		model.addAttribute("album",album);
		/*redirectAttributes.addFlashAttribute("album",album);*/
		
		return "redirect:list-photo";
	}
}