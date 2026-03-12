package portfolio.PhotoSharingApp.controller;

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
import portfolio.PhotoSharingApp.form.UpdatePasswordForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AccountService;

@Controller
public class UpdatePasswordController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountService accountService;

	@GetMapping("/update-password")
	public String getUpdatePassword(
			Model model
			,@ModelAttribute("updatePasswordForm")UpdatePasswordForm form
		) {
		
		return "account/update";
	}
	
	@PostMapping("/update-password")
	public String postUpdatePassword(
			Model model
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("updatePasswordForm")@Validated UpdatePasswordForm form
			,BindingResult bindingResult
		) throws Exception {
		
		/*パスワードの相関チェック*/
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