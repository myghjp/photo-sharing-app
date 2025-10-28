package portfolio.PhotoSharingApp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.form.EditAccountForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.UserService;

@Controller
public class EditAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/edit-account")
	public String getEditAccount(Model model
			,EditAccountForm editAccountForm
	){
		model.addAttribute("editAccountForm", editAccountForm);
		return "edit-account";
	}
	
	@PostMapping("/edit-account")
	public String postEditAccount (Model model
			,@ModelAttribute EditAccountForm editAccountForm
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,HttpSession session
			,RedirectAttributes redirectAttributes) {
		
		/*CSRF対策(後で)*/
		
		/*formをentityに変換*/
		Accounts accounts = modelMapper.map(editAccountForm, Accounts.class);
		
		/*ログインしているユーザーのID取得してセット*/
		accounts.setId(loginUserDetails.getUserId());
		
		/*パスワードをハッシュ化*/
		accounts.setPass(passwordEncoder.encode(accounts.getPass()));

		/*パスワードを変更*/
		userService.updateEditAccount(accounts);
		
		/*※パスワードを変更すると、ログアウトする(session破棄)*/
		session.invalidate();
		
		return "redirect:login";
	}
}