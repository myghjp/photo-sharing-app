package portfolio.PhotoSharingApp.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;

@Controller
@SessionAttributes(value = {"groups"})
public class HomeGroupController {
	
	@GetMapping("/home-group")
	public String getHomeGroup(Groups groups
			,RedirectAttributes redirectAttributes) {
		
		return "group/home-group";
	}

	/*
	ーー※A-1からE-4までがセットーー
	
	E-4 利用者削除時のアカウント名を表示させる
	
	↓最後
	C-3 ユーザ自身が作成したグループを削除しないとアカウントの削除を実行できない
	
	・user/passの調整(UserDetailsService)
	ーーーーーーーーーーーーーーーー
	*/
}