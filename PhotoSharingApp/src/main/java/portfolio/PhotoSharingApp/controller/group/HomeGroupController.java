package portfolio.PhotoSharingApp.controller.group;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.security.LoginUserDetails;

@Controller
@SessionAttributes(value = {"groups"})
public class HomeGroupController {
	
	@GetMapping("/home-group")
	public String getHomeGroup(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,Groups groups
			,RedirectAttributes redirectAttributes) {
		
		model.addAttribute("userId",loginUserDetails.getUserId());
		return "group/home-group";
	}

	/*
	ーー※A-1からE-4までがセットーー
	
	D-4 管理者のみ管理者情報を表示
	D-5 管理者以外はグループを削除ボタンを表示させない
	
	E-2 削除ボタンを表示させない/管理者が誰かを表示させる
	E-4 利用者削除時のアカウント名を表示させる
	
	↓最後
	C-3 ユーザ自身が作成したグループを削除しないとアカウントの削除を実行できない
	
	・user/passの調整(UserDetailsService)
	ーーーーーーーーーーーーーーーー
	*/
}