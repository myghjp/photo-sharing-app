package portfolio.PhotoSharingApp.controller.photo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${app.media.directory}")
	private String mediaDirectory;
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			,@ModelAttribute("album")Album album
		) {
		
		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getAccountId()) {
			model.addAttribute("isAdmin",true);
		}
		
		model.addAttribute("loginUser", user.getUsername());
		
		/*写真のテーブル情報とアカウント名を取得*/
		List<Photo> photoList = photoService.getphotoList(album.getId());
		model.addAttribute("photoList", photoList);
		
		return "photo/list-photo";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@RequestParam("multipartFile")MultipartFile file
			,RedirectAttributes redirectAttributes
			,@SessionAttribute("album")Album album
			)throws IOException {
		
		if (file.isEmpty()) {
			return "redirect:list-photo";
		}
		
		/*元のファイル名を取得*/
		String originalFilename = file.getOriginalFilename();
		/*画像保存先フォルダに保存する*/
		Path destPath = Paths.get(mediaDirectory, originalFilename);
		/*保存先ディレクトリがなければ作成する*/
		Files.createDirectories(destPath.getParent());
		/*アップロードしたファイルを保存*/
		Files.write(destPath, file.getBytes());
		
		Photo photo = new Photo();
		
		photo.setAlbumId(album.getId());
		photo.setAccountId(user.getAccountId());
		
		/*ファイル名を保存*/
		photo.setPhoto(file.getOriginalFilename());
		
		photoService.addPhoto(photo);
	
		return "redirect:list-photo";
	}
}