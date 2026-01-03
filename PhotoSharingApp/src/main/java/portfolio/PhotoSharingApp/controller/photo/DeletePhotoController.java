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

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Photo;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value = {"group","album"})
public class DeletePhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(Model model
			,@PathVariable("id") int photoId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@SessionAttribute("album")Album album
			,@SessionAttribute("group")Group group
			) {
		
		/*↓わかりやすくする*/
		/*ーーーーーーーーーーーーーーーーーーーーーーーー*/

		/*この写真のアルバムはこのグループのものでないなら*/
		if (photoService.isCurrentAlbum(photoId, album.getId())) {
			throw new AccessDeniedException("アクセス権無し(このグループの写真ではない");
		} 
		
		/*自身がこのアルバムのグループの管理者でないなら*/
		if (photoService.isC(group.getAccountId(),loginUserDetails.getUserId())) {
			
			/*この写真のアカウントIDと一致しないなら*/
			if (photoService.isB(photoId,loginUserDetails.getUserId())){
				throw new AccessDeniedException("アクセス権無し(自身の写真ではない)");
			} 
			
		}
		
		/*このidと画像パス情報を取得*/
		Photo photoData = photoService.getPhoto(photoId);
		model.addAttribute("photoData",photoData);
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		return "photo/delete-photo";
	}

	@PostMapping("/delete-photo")
	public String postDeletePhoto(Model model
			, @RequestParam("id") int photoId
			, RedirectAttributes redirectAttributes
			)throws IOException {

		Photo photoData = photoService.getPhoto(photoId);

		Path path = Path.of("src/main/resources/static/img/" + photoData.getPhoto());
		Files.delete(path);

		photoService.removePhoto(photoData.getId());

		return "redirect:list-photo";
	}
}