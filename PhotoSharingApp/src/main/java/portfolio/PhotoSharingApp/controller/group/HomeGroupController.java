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

	グループ作成時、重複エラー
	


	・D、E まとめる
	・D、E バインド/バリデーション
	・掲示板の作成
	ーーーー
	・html/bootstrapの調整
	・user/passの調整(UserDetailsService)
	・entryAccountController(Post)
	*/
}