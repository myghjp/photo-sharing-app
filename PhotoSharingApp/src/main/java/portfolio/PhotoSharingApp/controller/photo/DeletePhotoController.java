package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value = {"groups"})
public class DeletePhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(Model model
			,Groups groups
			,@PathVariable("id")int photoId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		Photos photos = new Photos();
		photos.setId(photoId);
		
		/*まずは自身がグループの利用者かを確認*/
		/*このグループIDの管理者か利用者に自身が含まれているか*/
		if (photoService.isGroupMembers(groups.getId)) {
			
			/*(管理者ならそのまま)*/
			if (photoService.isCurrentUser(photos.getId()) == loginUserDetails.getUserId()) {
				
				/*このidと画像パス情報を取得*/
				Photos photosData = photoService.getPhoto(photoId);
				model.addAttribute("photosData",photosData);
				
			}else {
				

				/*if利用者なら自分だけ
				 * 
				 * 一致すれば
				 * */
				
				/*このidと画像パス情報を取得*/
				Photos photosData = photoService.getPhoto(photoId);
				model.addAttribute("photosData",photosData);
				
				/*↓elseそれ以外はエラー*/
				
				
			}
			
		} else {
			
			/*↓それ以外はエラー*/
			throw new IllegalArgumentException("不正なIDですB");
		}
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		return"photo/delete-photo";

	}

	@PostMapping("/delete-photo")
	public String postDeletePhoto(Model model
			,@RequestParam("id")int photoId
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		Photos photosData = photoService.getPhoto(photoId);
		
		Path path = Path.of("src/main/resources/static/img/" + photosData.getPhoto());
		Files.delete(path);
		
		photoService.removePhoto(photosData.getId());
		
		return "redirect:list-photo";
	}
}