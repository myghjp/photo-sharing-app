package portfolio.PhotoSharingApp.controller.account;

import jakarta.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.form.account.EntryAccountForm;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Controller
public class EntryAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/entry-account")
	public String getEntryAccount(Model model
			,HttpSession session
			,EntryAccountForm entryAccountForm
			) {
		
		model.addAttribute("entryAccountForm", entryAccountForm);
		
		session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		
		return "account/entry-account";
	}
	
	@PostMapping("/entry-account")
	public String postEntryAcount(Model model
			,HttpSession session
			,@ModelAttribute @Validated EntryAccountForm entryAccountForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		Account account = modelMapper.map(entryAccountForm, Account.class);

		/*登録済のアカウント名の重複確認*/
		if (accountService.isUsernameExisting(account)) {
			bindingResult.rejectValue("username", "entryAccountNameError");
		}
		
		/*登録済のメールアドレスの重複確認*/
		if (accountService.isEmailAddressExisting(account)) {
			bindingResult.rejectValue("emailAddress", "entryAccountEmailError");
		}
		
		if (bindingResult.hasErrors()) {
			return getEntryAccount(model,session,entryAccountForm);
		}
		
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.createAccount(account);
		
		return "redirect:login";
	}
}