package portfolio.PhotoSharingApp.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
public class DeleteAccountController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/delete-account")
	public String getDeleteAccount() {

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,Accounts accounts
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,HttpSession session
			,RedirectAttributes redirectAttributes) {
		
		/*ログインしているユーザーのID取得してセット*/
		accounts.setId(loginUserDetails.getUserId());
		
		/*ログインユーザIDからAccountを削除する*/
		userService.deleteAccount(accounts);
		
		/*※パスワードを変更すると、ログアウトする(session破棄)*/
		session.invalidate();
		
		return "redirect:login";
	}
}