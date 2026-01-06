package portfolio.PhotoSharingApp.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Controller
public class DeleteAccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping("/delete-account")
	public String getDeleteAccount(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,boolean error
			) {
		
		/*↓不要になる*/
		
		/*※idが自身のアカウントIdと同じかを確認*/
		/*if (accountService.isCurrentAccount(id,loginUserDetails.getAccountId())) {
			throw new AccessDeniedException("不正なIDです");
		}*/
		
		model.addAttribute("id",loginUserDetails.getAccountId());
		model.addAttribute("isError",error);

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,@RequestParam("id")int id
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes
			) {
		
				/*@RequestParamも不要*/
	
		Account account = new Account();
		account.setId(id);
		
		/*自身が作成したグループが存在するか確認*/
		if (accountService.isCreateGroupExisting(account.getId())) {
			return getDeleteAccount(model,loginUserDetails,true);
		}
		
		accountService.removeAccount(account.getId());
		
		session.invalidate();
		
		return "redirect:login";
	}
}