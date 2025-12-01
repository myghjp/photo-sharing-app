package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
public class DeletePhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(Model model
			,@PathVariable("id")int id
			) {
		
		/*idと画像パス情報をまとめて取得*/
		Photos photoData = photoService.getPhoto(id);
		/*idと画像path*/
		
		
		/*ここで管理者情報も取得(未定)*/
		
		
		model.addAttribute("photoData",photoData);
		
		return "photo/delete-photo";
	}
	
	@PostMapping("/delete-photo")
	public String postDeletePhoto(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		Photos photoData = photoService.getPhoto(id);
		
		/*ファイルも削除したい*/
		Path path = Path.of("src/main/resources/static/img/" + photoData.getPhoto());
		Files.delete(path);
		
		/*データベースのパス情報を削除する*/
		photoService.removePhoto(photoData.getId());
		
		return "redirect:list-photo";
	}

}
