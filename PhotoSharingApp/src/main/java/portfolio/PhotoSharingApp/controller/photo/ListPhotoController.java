package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value = {"groups","albums"})
/*@Slf4j*/
public class ListPhotoController { 
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model
			,Groups groups
			,Albums albums
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes
			) {
		
		model.addAttribute("groupName",groups);
		model.addAttribute("albumName",albums.getAlbumName());
		
		
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		model.addAttribute("username", loginUserDetails.getUsername());
		
		
		/*アルバム内だけの写真を表示するようにする*/
		
		List<Photos> photoList = photoService.getphotoList(albums.getId());
		model.addAttribute("photoList", photoList);
		
		
		
		/*log.info(photoList.toString());*/
		
		return "photo/list-photo";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(Model model
			,Albums albums
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@RequestParam("photo")MultipartFile photo
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		if (photo.isEmpty()) {
			return "redirect:list-photo";
		}
		/*※パス情報直書き×(でもそのまま提出11月27日)*/
		Path path = Path.of("src/main/resources/static/img/" + photo.getOriginalFilename());
		Files.copy(photo.getInputStream(), path);
		
		/*ーー↓データベースにパス情報を登録するーーーーー*/
		Photos photos = new Photos();
		
		/*このアルバムのID*/
		photos.setAlbumId(albums.getId());

		/*自身のアカウントID*/
		photos.setAccountId(loginUserDetails.getUserId());
	
		/*画像パス情報？(String)*/
		photos.setPhoto(photo.getOriginalFilename());
		
		photoService.addPhoto(photos);
	
		/*ーーーーーーーーーーーーーーーーーーーーーーーー*/

		return "redirect:list-photo";
	}
}
