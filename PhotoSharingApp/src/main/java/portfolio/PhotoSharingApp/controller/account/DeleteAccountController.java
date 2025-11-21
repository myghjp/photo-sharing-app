package portfolio.PhotoSharingApp.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
@Slf4j
public class DeleteAccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;

	@GetMapping("/delete-account")
	public String getDeleteAccount(Model model
			,boolean bool
			
			) {
		
		model.addAttribute("bool",bool);

		return "account/delete-account";
	}
	
	@PostMapping("/delete-account")
	public String postDeleteAccount(Model model
			,Accounts accounts
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,HttpSession session
			,RedirectAttributes redirectAttributes) {
	
		accounts.setId(loginUserDetails.getUserId());
		
		/*帰ってくる値が0で削除ができない*/
		
		/*アカウントクラスが問題*/
		/*※バインドではないし
		 * 二択ではない
		 * ここで比較
		 * 
		 * booleanが間違い
		 * */
		
		if (groupService.groupAdmin(accounts.getId()) == 0) {
			return getDeleteAccount(model,true);
		} 
		
		/*ユーザ自身が作成したグループを削除しないとアカウントの削除を実行できない*/
		/*accounts.setId(loginUserDetails.getUserId());*/
		
		log.info(accounts.toString());

		/*ログインユーザIDからAccountを削除する*/
		userService.deleteAccount(accounts.getId());
		
		/*※パスワードを変更すると、ログアウトする(session破棄)*/
		session.invalidate();
		
		return "redirect:login";
		
		
	}
	
	
}