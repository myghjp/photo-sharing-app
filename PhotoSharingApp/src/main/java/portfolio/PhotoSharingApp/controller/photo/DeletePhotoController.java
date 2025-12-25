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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value= {"groups","albums"})
public class DeletePhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(Model model
			,Groups groups
			,Albums albums
			,@PathVariable("id") int photoId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
		) {

		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

		/*グループ管理者はグループ内アルバム写真を全て削除できる*/

		/*この写真のアルバムはこのグループのものか？
		 * */
		/*写真テーブルのアルバムIdとこのグループのアルバムIdの比較*/
		/*Photos photos = new Photos();
		photos.setId(photoId);*/
		
		System.out.println(albums.getId() + "deletePhotoController");
		 
		
		if (photoService.isCurrentAlbum(photoId,albums.getId())) {
		
		} else {
			throw new AccessDeniedException("アクセス権無しA");
		}

		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

		/*自身はこのグループの管理者か？*/

		/*グループ利用者は自身が投稿したグループ内写真を削除できる*/
		/*if (photoService.isCurrentUser(photoId,loginUserDetails.getUserId())) {
			
			このidと画像パス情報を取得
			Photos photosData = photoService.getPhoto(photoId);
			model.addAttribute("photosData",photosData);
			
		} else {
			throw new AccessDeniedException("アクセス権無しB");
		}*/

		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

		/*このidと画像パス情報を取得*/
		Photos photosData = photoService.getPhoto(photoId);
		model.addAttribute("photosData", photosData);

		return "photo/delete-photo";

	}

	@PostMapping("/delete-photo")
	public String postDeletePhoto(Model model, @RequestParam("id") int photoId, RedirectAttributes redirectAttributes)
			throws IOException {

		Photos photosData = photoService.getPhoto(photoId);

		Path path = Path.of("src/main/resources/static/img/" + photosData.getPhoto());
		Files.delete(path);

		photoService.removePhoto(photosData.getId());

		return "redirect:list-photo";
	}
}