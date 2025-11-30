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
			,Groups groups
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes) {
		
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("admin",true);
		}
		
		model.addAttribute("userId",loginUserDetails.getUserId());
		return "group/home-group";
		
	}
	
	/*ーーー追加が必要な機能/エラーチェックーーー*/
	
	/*H-2.写真一覧自身が追加した写真以外は削除ボタンを表示しない*/
	/*↑H-1は全て表示*/
	
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*グループ別、アルバム別のリスト表示の追加と確認*/
	
}