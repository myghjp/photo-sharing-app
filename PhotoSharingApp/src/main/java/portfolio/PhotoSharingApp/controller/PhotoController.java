package portfolio.PhotoSharingApp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Photo;
import portfolio.PhotoSharingApp.form.AddPhotoForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.PhotoService;

@Controller
@RequestMapping("/album/photo")
@SessionAttributes(value = {"group","album"})
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Value("${app.media.directory}")
	private String mediaDirectory;
	
	@GetMapping("/list")
	public String getList(
			Model model
			,@ModelAttribute("addPhotoForm")AddPhotoForm form
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
			,@ModelAttribute("album")Album album
		) {
		
		/*アルバム不選択だとリダイレクト*/
		if (album.getId() == null) {
			return "redirect:/album/list";
		}
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		/*写真の情報を取得*/
		List<Photo> photoList = photoService.getPhotoInfo(album.getId());
		model.addAttribute("photoList", photoList);
		
		return "photo/list";
	}
	
	@PostMapping("/list")
	public String postList(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("addPhotoForm")@Validated AddPhotoForm form
			,BindingResult bindingResult
			,@SessionAttribute("group")Group group
			,@SessionAttribute("album")Album album
		)throws IOException {
		
		MultipartFile file = form.getPhoto();
		
		if (file.isEmpty()) {
			bindingResult.rejectValue("photo", "addPhotoEmptyError");
		}
		
		if (bindingResult.hasErrors()) {
			return getList(model,form,user,group,album);
		}
		
		/*ファイルの拡張子を取得*/
		String originalFilename = file.getOriginalFilename();
		String extension = StringUtils.getFilenameExtension(originalFilename);
		// アップロードファイルはUUIDを使って重複しない名前に変更する
		String fileName = UUID.randomUUID().toString() + "." + extension;
		/*画像保存先フォルダに保存*/
		Path destPath = Paths.get(mediaDirectory, fileName);
		/*保存先ディレクトリがなければ作成*/
		Files.createDirectories(destPath.getParent());
		/*アップロードしたファイルを保存*/
		Files.write(destPath, file.getBytes());
		
		Photo photo = new Photo();
		
		photo.setAlbumId(album.getId());
		photo.setAccountId(user.getUserId());
		photo.setPhoto(fileName);
		
		photoService.add(photo);
	
		return "redirect:list";
	}
	
	@PostMapping("/delete")
	public String postDelete(
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

		return "redirect:list";
	}
}