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
@SessionAttributes(value = { "groups"})
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
	やること↓
	・Dグループ系バインド/バリデーション
	・E-1 利用者一覧の表示
	・E-3利用者追加、まとめる
	・Eグループメンバ系バインド/バリデーション
	※html画面の作成
	*/
}