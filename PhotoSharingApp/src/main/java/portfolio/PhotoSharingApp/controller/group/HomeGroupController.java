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
		
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("admin",true);
		}
		
		model.addAttribute("userId",loginUserDetails.getUserId());
		return "group/home-group";
		
	}
	
	/*ーーー追加が必要な機能/エラーチェックーーー*/
	/*D-5.管理者以外(未確認)*/
	/*E-1,(利用者一覧管理画面) unknownの確認をする*/
	/*E-2,(利用者一覧管理外画面) unknownの確認をする list外の管理者情報を用意する*/
	/*E-3, メールアドレスの形式か(再確認)、追加したアドレスと重複していないか(再確認)*/
	/*E-4,利用者削除 unknownの確認をする*/
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*F-1,掲示板 自身が追加したコメント以外は、削除ボタンを表示しない unknownの確認をする*/
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*G-2 アルバム削ボタン無し*/
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*H-2.写真一覧自身が追加した写真以外は削除ボタンを表示しない*/
	/*↑H-1は全て表示*/
	/*ーーーーーーーーーーーーーーーーーーーーーーー*/
	/*グループ別、アルバム別のリスト表示の追加と確認*/
	
}