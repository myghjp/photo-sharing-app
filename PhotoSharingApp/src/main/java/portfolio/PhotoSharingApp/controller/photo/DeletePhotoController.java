package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value = {"groups","albums"})
public class DeletePhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(Model model
			,@PathVariable("id") int photoId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@SessionAttribute("albums")Albums albums
			,@SessionAttribute("groups")Groups groups
			) {
		
		/*↓わかりやすく*/

		/*この写真のアルバムはこのグループのものでないなら？*/
		if (photoService.isCurrentAlbum(photoId, albums.getId())) {
			throw new AccessDeniedException("アクセス権無し(このグループの写真ではない");
		} 
		
		/*自身がこのアルバムのグループの管理者でないなら*/
		if (photoService.isC(groups.getAccountId(),loginUserDetails.getUserId())) {
			
			/*この写真のアカウントIDと一致しなければ？←？*/
			if (photoService.isB(photoId,loginUserDetails.getUserId())){
				throw new AccessDeniedException("アクセス権無し(自身の写真ではない)");
			} 
			
		}

		/*このidと画像パス情報を取得*/
		Photos photosData = photoService.getPhoto(photoId);
		model.addAttribute("photosData",photosData);
		
		return "photo/delete-photo";
	}

	@PostMapping("/delete-photo")
	public String postDeletePhoto(Model model
			, @RequestParam("id") int photoId
			, RedirectAttributes redirectAttributes
			)throws IOException {

		Photos photosData = photoService.getPhoto(photoId);

		Path path = Path.of("src/main/resources/static/img/" + photosData.getPhoto());
		Files.delete(path);

		photoService.removePhoto(photosData.getId());

		return "redirect:list-photo";
	}
}