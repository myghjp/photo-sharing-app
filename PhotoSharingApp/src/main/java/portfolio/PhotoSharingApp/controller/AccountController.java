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
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.form.CreateAccountForm;
import portfolio.PhotoSharingApp.form.UpdatePasswordForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AccountService;
import portfolio.PhotoSharingApp.service.GroupService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountService accountService;

	@Autowired
	private GroupService groupService;

	@GetMapping("/create")
	public String getCreate(
			HttpSession session
			,@ModelAttribute("createAccountForm") CreateAccountForm form
		) {

		session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");

		return "account/create";
	}
	
	@GetMapping("/update-password")
	public String getUpdatePassword(
			Model model
			,@ModelAttribute("updatePasswordForm")UpdatePasswordForm form
		) {
		
		return "account/update-password";
	}
	
	@GetMapping("/delete")
	public String getDeleteAccount() {
		
		return "account/delete";
	}

	@PostMapping("/create")
	public String postCreate(
			HttpSession session
			,@ModelAttribute("createAccountForm")@Validated CreateAccountForm form
			,BindingResult bindingResult
		) {

		Account account = modelMapper.map(form, Account.class);

		/*アカウント名が重複しているか*/
		if (accountService.isUsernameDuplicate(account.getUsername())) {
			bindingResult.rejectValue("username", "createAccountNameError");
		}

		/*メールアドレスが重複しているか*/
		if (accountService.isEmailAddressDuplicate(account.getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "createAccountEmailError");
		}

		if (bindingResult.hasErrors()) {
			return getCreate(session, form);
		}

		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.create(account);

		return "redirect:login";
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

	@PostMapping("/delete")
	public String postDelete(
			Model model
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails user
		) {
		
		/*作成したグループが存在しているか*/
		if (groupService.createdGroupExists(user.getUserId())) {
			
			boolean error = true;
			model.addAttribute("hasError", error);
			
			return "account/delete";
		}
		
		accountService.remove(user.getUserId());
		
		session.invalidate();
		
		return "redirect:login";
	}
}