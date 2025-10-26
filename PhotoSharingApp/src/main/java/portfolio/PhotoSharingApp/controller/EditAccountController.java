package portfolio.PhotoSharingApp.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.form.EditAccountForm;
import portfolio.PhotoSharingApp.service.UserService;

@Controller
public class EditAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*コントローラクラスはまとめる？*/

	@GetMapping("/edit-account")
	public String getEditAccount(Model model
			,EditAccountForm editAccountForm
	){
		model.addAttribute("editAccountForm", editAccountForm);
		return "edit-account";
		
		/*テスト*/
		system.out.println();
	}
	
	@PostMapping("/edit-account")
	public String postEditAccount (Model model
			,@ModelAttribute EditAccountForm editAccountForm
			,RedirectAttributes redirectAttributes) {
		
		
		
		/*ログインしているアカウントの、パスワードを変更*/
		
		/*※パスワードを変更すると、ログアウトする*/
		return "redirect:login";
	}
}