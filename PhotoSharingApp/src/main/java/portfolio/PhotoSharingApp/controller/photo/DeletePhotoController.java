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
import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value = {"albums"})
public class DeletePhotoController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/delete-photo/{id}")
	public String getDeletePhoto(Model model
			,@PathVariable("id") int photoId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,Albums albums
			) {
		
		
		/*PathのphotoIdを自動でalbumにsetされている(引数のとき)*/
		
		/*xmlのせい？
		 * photos.id photos.albumId
		 * albums.id 
		 * */
		
		System.out.println(albums);
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

		/*この写真のアルバムはこのグループのものか？*/
		/*写真テーブルのアルバムIdとこのグループのアルバムIdの比較*/
		
		if (photoService.isCurrentAlbum(photoId, albums.getId())) {
			
			/*このidと画像パス情報を取得*/
			Photos photosData = photoService.getPhoto(photoId);
			model.addAttribute("photosData", photosData);
			
		
		} else {
			throw new AccessDeniedException("アクセス権無しA");
		}

		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*グループ管理者はグループ内アルバム写真を全て削除できる*/

		/*グループ利用者は自身が投稿したグループ内写真を削除できる*/
		/*if (photoService.isCurrentUser(photoId,loginUserDetails.getUserId())) {
			
			このidと画像パス情報を取得
			Photos photosData = photoService.getPhoto(photoId);
			model.addAttribute("photosData",photosData);
			
		} else {
			throw new AccessDeniedException("アクセス権無しB");
		}*/

		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

		

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