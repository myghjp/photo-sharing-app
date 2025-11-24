package portfolio.PhotoSharingApp.controller.photo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListPhotoController {
	
	@GetMapping("/list-photo")
	public String getListPhoto(Model model) {
		
		
		/*取得したアルバムIDと
		使用しているグループIDを使い
		写真一覧を取得する*/
		
		/*グループ名も必要*/
		
		return "photo/list-photo";
	}

}
