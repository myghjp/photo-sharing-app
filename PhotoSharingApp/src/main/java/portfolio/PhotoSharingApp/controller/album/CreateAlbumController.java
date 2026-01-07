package portfolio.PhotoSharingApp.controller.album;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"group"})
public class CreateAlbumController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/create-album")
	public String getCreateAlbum(Model model
			,CreateAlbumForm form
			) {
		
		model.addAttribute("createAlbumForm", form);
			
		return "album/create-album";
	}
	
	@PostMapping("/create-album")
	public String postCreateAlbum(Model model
			,Group group
			,@ModelAttribute @Validated CreateAlbumForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		if (bindingResult.hasErrors()) {
			return getCreateAlbum(model,form);
		}
		
		Album album = modelMapper.map(form,Album.class);
		
		album.setGroupId(group.getId());
		albumService.addAlbum(album);
		
		return "redirect:select-album";
	}
}