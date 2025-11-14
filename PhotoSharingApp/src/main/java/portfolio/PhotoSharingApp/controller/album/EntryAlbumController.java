package portfolio.PhotoSharingApp.controller.album;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.form.album.EntryAlbumForm;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"groups"})
public class EntryAlbumController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/entry-album")
	public String getEntryAlbum(Model model
			,EntryAlbumForm entryAlbumForm) {
		
		return "album/entry-album";
	}
	
	@PostMapping("/entry-album")
	public String postEntryAlbum(Model model
			,Groups groups
			,@ModelAttribute EntryAlbumForm entryAlbumForm
			,RedirectAttributes redirectAttributes
			) {
		/*グループIDとアルバム名を登録する*/
		
		Albums albums = modelMapper.map(entryAlbumForm,Albums.class);
		
		albums.setGroupId(groups.getId());
		
		albumService.insertEntryAlbum(albums);
		
		return "redirect:select-album";
	}
	
	

}
