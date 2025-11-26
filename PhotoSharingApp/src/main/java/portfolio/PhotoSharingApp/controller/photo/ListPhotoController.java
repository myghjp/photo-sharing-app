package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.security.LoginUserDetails;

@Controller
/*@SessionAttributes(value = {"groups"})*/
@SessionAttributes(value = {"groups","albums"})
/*@Slf4j*/
public class ListPhotoController { 
	
	/*@Autowired
	private PhotoService photoService;*/
	
	/*@ModelAttribute(value = "groups")
	public Groups groups() {
		return new Groups();
	}*/
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model
			,Groups groups
			,Albums albums
			,RedirectAttributes redirectAttributes
			) {
		
		model.addAttribute("groupName",groups.getGroupName());
		model.addAttribute("albumName",albums.getAlbumName());
		
		/*albums.getAlbumName()↓*/
		
			/*↓このアルバムIDのListを表示(後に編集)*/
			/*List<Photos> photoList = photoService.getphotoList();
			model.addAttribute("photoList", photoList);*/
		
		return "photo/list-photo";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@RequestParam("photo")MultipartFile photo
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		if (photo.isEmpty()) {
			return "redirect:list-photo";
		}
		
		/*※パス情報直書き×*/
		Path path = Path.of("src/main/resources/static/img/" + photo.getOriginalFilename());
		Files.copy(photo.getInputStream(), path);
		
		
		/*ーー↓データベースにパス情報を登録するーーーーー*/
		
		/*このアルバムのID(仮)*/
		/*photos.setAlbumId(4);*/

		/*自身のアカウントID*/
		/*photos.setAccountId(loginUserDetails.getUserId());*/
	
		/*画像パス情報？(String)*/
		/*photos.setPhoto(photo.getOriginalFilename());*/
	
		/*log.info(photos.toString());*/
		
		/*photoService.addPhoto(photos);*/
	
		/*ーーーーーーーーーーーーーーーーーーーーーーーー*/

		return "redirect:list-photo";
	}
}
