package portfolio.PhotoSharingApp.controller.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Controller
@SessionAttributes(value = {"groups"})
public class SelectAlbumController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/select-album")
	public String getSelectAlbum(Model model
			,Groups groups
			,RedirectAttributes redirectAttributes) {
		
		/*ここでアルバム一覧を取得*/
		model.addAttribute("groupName",groups.getGroupName());
		
		List<Albums> albumList = albumService.getAlbumList();
		model.addAttribute("albumList", albumList);
		
		return "album/select-album";
	}
	
	@PostMapping("/select-album")
	public String postSelectAlbum(Model model
			,Groups groups
			,@RequestParam("id")int albumId
			,RedirectAttributes redirectAttributes) {
		
		int groupId = groups.getId();
		
		
		
		/*アルバムもセッション？*/
		
		/*取得したアルバムIDと
		使用しているグループIDを使い
		写真一覧を取得する*/
		
		
		/*※ここは表示*/
		/*グループ名*/
		/*アルバム名*/
		
		
		
		
		
		/*model.addAttribute("groupName",groups.getGroupName());*/
		
		/*	List<Albums> albumList = albumService.getAlbumList();*/
		/*model.addAttribute("albumList", albumList);*/
		
		return "redirect:list-photo";
	}
}
