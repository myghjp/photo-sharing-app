package portfolio.PhotoSharingApp.controller.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.form.account.CreateAccountForm;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Controller
public class CreateAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/create-account")
	public String getCreateAccount(
			HttpSession session
			,@ModelAttribute("createAccountForm")CreateAccountForm form
			) {
		
		session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		
		return "account/create-account";
	}
	
	@PostMapping("/create-account")
	public String postCreateAcount(
			HttpSession session
			,@ModelAttribute("createAccountForm") @Validated CreateAccountForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		Account account = modelMapper.map(form, Account.class);

		/*登録済のアカウント名と重複していないかを確認*/
		if (accountService.existsByUsername(account.getUsername())) {
			bindingResult.rejectValue("username", "entryAccountNameError");
		}
		
		/*登録済のメールアドレスと重複していないかを確認*/
		if (accountService.existsByEmail(account.getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "entryAccountEmailError");
		}
		
		if (bindingResult.hasErrors()) {
			return getCreateAccount(session,form);
		}
		
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.create(account);
		
		return "redirect:login";
	}
}