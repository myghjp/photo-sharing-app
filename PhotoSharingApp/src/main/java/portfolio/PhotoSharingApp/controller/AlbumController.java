package portfolio.PhotoSharingApp.controller;

import java.util.List;

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

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.CreateAlbumForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AlbumService;

@Controller
@SessionAttributes(value = {"group","album"})
public class AlbumController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlbumService albumService;
	
	@ModelAttribute(value = "album")
	public Album album() {
		return new Album();
	}
	
	@GetMapping("/list-album")
	public String getListAlbum(
			Model model
			,@ModelAttribute("createAlbumForm")CreateAlbumForm form
			,HttpSession httpSession
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
			) {
		
		httpSession.removeAttribute("album");
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*グループアルバムの情報を取得*/
		List<Album> albumList = albumService.getGroupAlbumInfo(group.getId());
		model.addAttribute("albumList", albumList);
		
		/*グループのアルバム数を取得*/
		int albumCount =  albumService.getGroupAlbumCount(group.getId());
		model.addAttribute("albumCount", albumCount);
	    
		return "album/list";
	}
	
	@PostMapping("/list-album")
	public String postListAlbum(
			Model model
			,@RequestParam("id")int albumId
			) {
		
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
			,@ModelAttribute("group")Group group
			) {
		
		if (bindingResult.hasErrors()) {
			return getListAlbum(model,form,httpSession,user,group);
		}
		
		Album album = modelMapper.map(form,Album.class);
		
		album.setGroupId(group.getId());
		albumService.add(album);
		
		return "redirect:list-album";
	}
	
	@PostMapping("/delete-album")
	public String postDeleteAlbum(
			@RequestParam("id") int albumId
			,@AuthenticationPrincipal LoginUserDetails user
			) {
		
		/*アルバム作成者か*/
		if (albumService.isAlbumCreator(albumId,user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		albumService.delete(albumId);
		
		return "redirect:list-album";
	}
}