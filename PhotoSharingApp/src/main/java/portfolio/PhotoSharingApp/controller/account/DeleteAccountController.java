package portfolio.PhotoSharingApp.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Controller
public class DeleteAccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping("/delete-account")
	public String getDeleteAccount(Model model
			,boolean error
			) {
		
		model.addAttribute("isError",error);

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails user
			,RedirectAttributes redirectAttributes
			) {
		
		/*自身が作成したグループが存在するかを確認*/
		if (accountService.isFindCreateGroup(user.getAccountId())) {
			return getDeleteAccount(model,true);
		}
		
		accountService.removeAccount(user.getAccountId());
		
		session.invalidate();
		
		return "redirect:login";
	}
}