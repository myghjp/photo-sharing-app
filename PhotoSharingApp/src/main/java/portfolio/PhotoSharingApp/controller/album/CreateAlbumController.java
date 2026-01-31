package portfolio.PhotoSharingApp.controller.album;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.album.CreateAlbumForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"group"})
public class CreateAlbumController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/create-album")
	public String getCreateAlbum(
			Model model
			,CreateAlbumForm form
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
			) {
		
		 model.addAttribute("createAlbumForm", form);
		
		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		boolean isActive = true;
	    model.addAttribute("isActiveAlbum", isActive);
		
		return "album/create-album";
	}
	
	@PostMapping("/create-album")
	public String postCreateAlbum(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("createAlbumForm") @Validated CreateAlbumForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			) {
		
		if (bindingResult.hasErrors()) {
			return getCreateAlbum(model,form,user,group);
		}
		
		Album album = modelMapper.map(form,Album.class);
		
		album.setGroupId(group.getId());
		albumService.add(album);
		
		return "redirect:select-album";
	}
}