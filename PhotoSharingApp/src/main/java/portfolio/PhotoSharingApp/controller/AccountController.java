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
import portfolio.PhotoSharingApp.form.CreateAccountForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AccountService;
import portfolio.PhotoSharingApp.service.GroupService;

@Controller
public class AccountController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountService accountService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/create-account")
	public String getCreateAccount(
			HttpSession session, @ModelAttribute("createAccountForm") CreateAccountForm form) {

		session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");

		return "account/create";
	}
	
	@GetMapping("/delete-account")
	public String getDeleteAccount() {
		
		return "account/delete";
	}
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

	@PostMapping("/create-account")
	public String postCreateAcount(
			HttpSession session, @ModelAttribute("createAccountForm") @Validated CreateAccountForm form,
			BindingResult bindingResult) {

		Account account = modelMapper.map(form, Account.class);

		/*アカウント名が重複しているか*/
		if (accountService.isUsernameDuplicate(account.getUsername())) {
			bindingResult.rejectValue("username", "entryAccountNameError");
		}

		/*メールアドレスが重複しているか*/
		if (accountService.isEmailAddressDuplicate(account.getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "entryAccountEmailError");
		}

		if (bindingResult.hasErrors()) {
			return getCreateAccount(session, form);
		}

		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.create(account);

		return "redirect:login";
	}

	@PostMapping("/delete-account")
	public String postDeleteAccount(
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