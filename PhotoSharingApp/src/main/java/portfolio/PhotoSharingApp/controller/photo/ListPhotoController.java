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

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Photo;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Controller
@SessionAttributes(value = {"group","album"})
public class ListPhotoController { 
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("album")Album album
			,@ModelAttribute("group")Group group
			,RedirectAttributes redirectAttributes
		) {
		
		/*一致すると自身は管理者である*/
		if (group.getAccountId() == loginUserDetails.getAccountId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*管理者以外は自身のボタンだけを表示*/
		model.addAttribute("username", loginUserDetails.getUsername());
		
		
		/*photosテーブルの情報とアカウント名を取得*/
		List<Photo> photoList = photoService.getphotoList(album.getId());
		model.addAttribute("photoList", photoList);
		
		return "photo/list-photo";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(Model model
			,@SessionAttribute("album")Album albums
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@RequestParam("photo")MultipartFile photoPath
			,RedirectAttributes redirectAttributes
			)throws IOException {
		
		if (photoPath.isEmpty()) {
			return "redirect:list-photo";
		}
		
		try {
			Path path = Path.of("src/main/resources/static/img/" + photoPath.getOriginalFilename());
			Files.copy(photoPath.getInputStream(), path);
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("isError",true);
			return "redirect:list-photo";
		}
		
		Photo photo = new Photo();
		
		photo.setAlbumId(albums.getId());
		photo.setAccountId(loginUserDetails.getAccountId());
		photo.setPhoto(photoPath.getOriginalFilename());
		
		photoService.addPhoto(photo);
	
		return "redirect:list-photo";
	}
}