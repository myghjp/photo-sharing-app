package portfolio.PhotoSharingApp.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
public class DeleteAccountController {
	
	@Autowired
	private UserService userService;
	
	/*@Autowired
	private GroupService groupService;*/

	@GetMapping("/delete-account/{userId}")
	public String getDeleteAccount(Model model
			,@PathVariable("userId")int id
			,boolean bool
			) {
		
		model.addAttribute("id",id);
		model.addAttribute("isError",bool);

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,@RequestParam("id")int id
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes
			) {
	
		Accounts accounts = new Accounts();
		accounts.setId(id);
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*※idが自身のアカウントIdと同じかを確認*/
		if (userService.isCurrentUser(accounts.getId()) != loginUserDetails.getUserId()) {
			throw new IllegalArgumentException("不正なIDです");
		}
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*自身が作成したグループが存在するか確認*/
		if (userService.isCreateGroupExisting(accounts.getId())) {
			return getDeleteAccount(model,id,true);
		}
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		userService.removeAccount(accounts.getId());
		
		session.invalidate();
		
		return "redirect:login";
	}
}