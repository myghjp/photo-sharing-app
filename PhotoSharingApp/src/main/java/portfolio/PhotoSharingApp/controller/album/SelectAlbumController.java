package portfolio.PhotoSharingApp.controller.album;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.album.CreateAlbumForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"group","album"})
public class SelectAlbumController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlbumService albumService;
	
	@ModelAttribute(value = "album")
	public Album album() {
		return new Album();
	}
	
	@GetMapping("/select-album")
	public String getSelectAlbum(
			Model model
			,CreateAlbumForm form
			,HttpSession httpSession
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
			) {
		
		httpSession.removeAttribute("album");
		model.addAttribute("createAlbumForm", form);
		
		/*このグループのアルバム情報を取得*/
		List<Album> albumList = albumService.findAllById(group.getId());
		model.addAttribute("albumList", albumList);
		
		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		boolean isActive = true;
	    model.addAttribute("isActiveAlbum", isActive);
		
		return "album/select-album";
	}
	
	@PostMapping("/select-album")
	public String postSelectAlbum(
			Model model
			,@RequestParam("id")int albumId
			,RedirectAttributes redirectAttributes
			) {
		
		/*アルバムIDとアルバム名を取得*/
		Album album = albumService.findById(albumId);
		model.addAttribute("album",album);
		
		return "redirect:list-photo";
	}
	
	@PostMapping("/create-album")
	public String postCreateAlbum(
			Model model
			,HttpSession httpSession
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("createAlbumForm") @Validated CreateAlbumForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			) {
		
		if (bindingResult.hasErrors()) {
			return getSelectAlbum(model,form,httpSession,user,group);
		}
		
		Album album = modelMapper.map(form,Album.class);
		
		album.setGroupId(group.getId());
		albumService.add(album);
		
		return "redirect:select-album";
	}
	
	@PostMapping("/delete-album")
	public String postDeleteAlbum(
			@RequestParam("id") int albumId
			,@AuthenticationPrincipal LoginUserDetails user
			,RedirectAttributes redirectAttributes
			) {
		
		Album album = new Album();
		album.setId(albumId);
		
		/*自身が作成したアルバムであるかを確認*/
		if (albumService.isAlbum(album.getId(),user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		albumService.delete(album.getId());

		return "redirect:select-album";
	}
}