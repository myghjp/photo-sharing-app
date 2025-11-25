package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;

@Controller
@SessionAttributes(value = {"groups"})
public class ListPhotoController { 
	
	/*@Autowired
	private PhotoService photoService;*/
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model
			,RedirectAttributes redirectAttributes
			
			/*先にformで渡す？*/
			
			
			) {
		
		return "photo/list-photo";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(
			Groups groups
			,@RequestParam("photo")MultipartFile photo
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		if (photo.isEmpty()) {
			return "redirect:list-photo";
		}
		
		Path photoPath = Path.of("src/main/resources/static/img/" + photo.getOriginalFilename());
		Files.copy(photo.getInputStream(),photoPath);
		
		/*ーー↓データベースにパス情報を登録するーーーーー*/
		
		/*このアルバムのID*/
		
		/*自身のアカウントID*/
		
		/*画像パス情報？(Pathクラス)*/
		
		
		
		
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーー*/

		return "redirect:list-photo";
	}
}
