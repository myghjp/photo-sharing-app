package portfolio.PhotoSharingApp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
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

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Photo;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.PhotoService;

@Controller
@SessionAttributes(value = {"group","album"})
public class PhotoController { 
	
	@Autowired
	private PhotoService photoService;
	
	@Value("${app.media.directory}")
	private String mediaDirectory;
	
	@GetMapping("/list-photo")
	public String getListPhoto(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
			,@ModelAttribute("album")Album album
		) {
		
		/*アルバム不選択だとリダイレクト*/
		if (album.getId() == null) {
			return "redirect:list-album";
		}
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*写真の情報を取得*/
		List<Photo> photoList = photoService.getPhotoInfo(album.getId());
		model.addAttribute("photoList", photoList);
		
		/*※*/
		model.addAttribute("loginUser",user.getUsername());
		
		return "photo/list";
	}
	
	@PostMapping("/list-photo")
	public String postListPhoto(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@RequestParam("multipartFile") MultipartFile file
			,@SessionAttribute("album")Album album
			)throws IOException {
		
		if (file.isEmpty()) {
			return "redirect:list-photo";
		}
		
		/*元のファイル名を取得*/
		String originalFilename = file.getOriginalFilename();
		/*画像保存先フォルダに保存*/
		Path destPath = Paths.get(mediaDirectory, originalFilename);
		/*保存先ディレクトリがなければ作成*/
		Files.createDirectories(destPath.getParent());
		/*アップロードしたファイルを保存*/
		Files.write(destPath, file.getBytes());
		
		Photo photo = new Photo();
		
		photo.setAlbumId(album.getId());
		photo.setAccountId(user.getUserId());
		photo.setPhoto(file.getOriginalFilename());
		
		photoService.add(photo);
	
		return "redirect:list-photo";
	}
	
	@PostMapping("/delete-photo")
	public String postDeletePhoto(
			Model model
			,@RequestParam("id") int photoId
			,@AuthenticationPrincipal LoginUserDetails user
			,@SessionAttribute("album")Album album
			,@SessionAttribute("group")Group group
			) throws IOException {
		
		/*アルバムの写真か*/
		if (photoService.isAlbumPhoto(photoId, album.getId())) {
			throw new AccessDeniedException("アクセス権がありません");
		} 
		
		/*グループ管理者ではないか*/
		if (group.getAccountId() != user.getUserId()) {
			
			/*写真追加者か*/
			if (photoService.isUploader(photoId,user.getUserId())){
				throw new AccessDeniedException("アクセス権がありません");
			} 
		}

		/*この写真のIDとパス情報を取得*/
		Photo photoData = photoService.findById(photoId);

		Path path = Path.of(mediaDirectory + photoData.getPhoto());
		Files.delete(path);

		photoService.remove(photoData.getId());

		return "redirect:list-photo";
	}
}