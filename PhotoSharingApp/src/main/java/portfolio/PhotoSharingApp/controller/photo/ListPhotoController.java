package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
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
public class ListPhotoController { 
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("albums")Albums albums
			,@ModelAttribute("groups")Groups groups
			,RedirectAttributes redirectAttributes
		) {
		
		
		/*一致すると自身は管理者である*/
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*管理者以外は自身のボタンだけを表示*/
		model.addAttribute("username", loginUserDetails.getUsername());
		
		
		/*photosテーブルの情報とアカウント名を取得*/
		List<Photos> photosList = photoService.getphotoList(albums.getId());
		model.addAttribute("photosList", photosList);
		
		return "photo/list-photo";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(Model model
			,@SessionAttribute("albums")Albums albums
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@RequestParam("photo")MultipartFile photo
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		if (photo.isEmpty()) {
			return "redirect:list-photo";
		}
		
		try {
			Path path = Path.of("src/main/resources/static/img/" + photo.getOriginalFilename());
			Files.copy(photo.getInputStream(), path);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("isError",true);
			return "redirect:list-photo";
		}
		
		Photos photos = new Photos();
		
		photos.setAlbumId(albums.getId());
		photos.setAccountId(loginUserDetails.getUserId());
		photos.setPhoto(photo.getOriginalFilename());
		
		photoService.addPhoto(photos);
	
		return "redirect:list-photo";
	}
}