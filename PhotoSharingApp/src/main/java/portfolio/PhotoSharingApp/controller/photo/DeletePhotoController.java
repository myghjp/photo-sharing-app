package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
			/*,@AuthenticationPrincipal LoginUserDetails loginUserDetails*/
			) {
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		
		
		/*グループ管理者はグループ内アルバム写真を全て削除できる*/
		/*グループ利用者は自身が投稿したグループ内写真を削除できる*/
		
		/*自身のログイン情報×*/
		/*自身のグループ情報×？*/
		/*自身のアルバム情報×？*/
		/*セッター(controllerでできること)*/
		
		/*※二つの引数の値を二種類のリポジトリをserviceで比較？*/
		/*serviceをserviceに入れない*/
		/*serviceを変数*/
		/*controller受け取りだけ？*/
		
		
		/*photoIdだけで比較せずに先に取得もできる*/
		Photos photosDataA = photoService.getSelectAll(photoId);
		
		/*この写真のアルバムはこのグループのものか？*/
		if (photoService.isCurrentUser(photosDataA.getId(),groups.getId())) {
			
			/*このidと画像パス情報を取得*/
			Photos photosData = photoService.getPhoto(photosDataA.getId());
			model.addAttribute("photosData",photosData);
			
		}else {
			throw new AccessDeniedException("アクセス権無し");
		}
		
		
		/*この写真のアカウントは自身のものか？？*/
		/*そうでなくてもこの写真のグループの管理者か？*/
		
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