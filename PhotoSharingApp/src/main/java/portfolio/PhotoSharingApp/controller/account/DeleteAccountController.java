package portfolio.PhotoSharingApp.controller.account;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
public class DeleteAccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;

	@GetMapping("/delete-account")
	public String getDeleteAccount(Model model
			,boolean bool
			) {
		
		model.addAttribute("isBool",bool);

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,Accounts accounts
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes
			) {
	
		accounts.setId(loginUserDetails.getUserId());
		
		if (groupService.groupAdmin(accounts.getId())) {
			return getDeleteAccount(model,true);
		} 
		
		userService.removeAccount(accounts.getId());
		
		session.invalidate();
		
		return "redirect:login";
	}
}