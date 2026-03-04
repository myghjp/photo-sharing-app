package portfolio.PhotoSharingApp.controller.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.form.account.UpdatePasswordForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AccountService;

@Controller
public class UpdatePasswordController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/update-password")
	public String getUpdatePassword(
			Model model
			,@ModelAttribute("updatePasswordForm")UpdatePasswordForm form
			) {
		
		return "account/update-password";
	}
	
	@PostMapping("/update-password")
	public String postUpdatePassword(
			Model model
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("updatePasswordForm")@Validated UpdatePasswordForm form
			,BindingResult bindingResult
			) throws Exception {
		
		/*パスワード再設定の相関チェック*/
		if (form.isPasswordValid()) {
			bindingResult.rejectValue("passwordConfirmation", "updatePasswordError");
		}
		
		if (bindingResult.hasErrors()) {
			return getUpdatePassword(model,form);
		}
		
		Account account = modelMapper.map(form, Account.class);
		
		account.setId(user.getUserId());
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.edit(account);
		
		session.invalidate();
		
		return "redirect:login";
	}
}