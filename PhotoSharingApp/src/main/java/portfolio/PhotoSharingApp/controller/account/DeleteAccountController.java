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
		
		model.addAttribute("isError",bool);

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes
			) {
	
		Accounts accounts = new Accounts();
		accounts.setId(loginUserDetails.getUserId());
		
		/*自身が作成したグループが残っていないかを確認*/
		if (groupService.isCreateGroupExisting(accounts.getId())) {
			return getDeleteAccount(model,true);
		} 
		
		userService.removeAccount(accounts.getId());
		
		session.invalidate();
		
		return "redirect:login";
	}
}