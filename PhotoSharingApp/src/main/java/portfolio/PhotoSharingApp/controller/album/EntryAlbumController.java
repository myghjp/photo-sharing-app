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
import portfolio.PhotoSharingApp.form.album.EntryAlbumForm;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"group"})
public class EntryAlbumController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/entry-album")
	public String getEntryAlbum(Model model
			,EntryAlbumForm entryAlbumForm
			) {
		
		model.addAttribute("entryAlbumForm", entryAlbumForm);
			
		return "album/entry-album";
	}
	
	@PostMapping("/entry-album")
	public String postEntryAlbum(Model model
			,Group group
			,@ModelAttribute @Validated EntryAlbumForm entryAlbumForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		if (bindingResult.hasErrors()) {
			return getEntryAlbum(model,entryAlbumForm);
		}
		
		Album album = modelMapper.map(entryAlbumForm,Album.class);
		
		album.setGroupId(group.getId());
		
		albumService.addAlbum(album);
		
		return "redirect:select-album";
	}
}