package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${app.media.directory}")
	private String mediaDirectory;

	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(
			Model model
			,@PathVariable("id") int photoId
			,@AuthenticationPrincipal LoginUserDetails user
			,@SessionAttribute("album")Album album
			,@SessionAttribute("group")Group group
			) {

		/*このアルバムの写真なのかを確認*/
		if (photoService.isAlbum(photoId, album.getId())) {
			throw new AccessDeniedException("アクセス権がありません");
		} 
		
		/*自身がグループの管理者ではないかを確認*/
		if (group.getAccountId() != user.getUserId()) {
			
			/*自身が追加した写真なのかを確認*/
			if (photoService.isPhoto(photoId,user.getUserId())){
				throw new AccessDeniedException("アクセス権がありません");
			} 
		}
		
		/*この写真のIDとパス情報を取得*/
		Photo photoData = photoService.findById(photoId);
		model.addAttribute("photoData",photoData);
		
		return "photo/delete-photo";
	}

	@PostMapping("/delete-photo")
	public String postDeletePhoto(
			Model model
			, @RequestParam("id") int photoId
			, RedirectAttributes redirectAttributes
			)throws IOException {

		/*この写真のIDとパス情報を取得*/
		Photo photoData = photoService.findById(photoId);
		
		Path path = Path.of(mediaDirectory + photoData.getPhoto());
		Files.delete(path);
		
		photoService.remove(photoData.getId());
		
		return "redirect:list-photo";
	}
}