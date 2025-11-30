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

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.form.account.EntryAccountForm;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
public class EntryAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
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
		
		Accounts accounts = modelMapper.map(entryAccountForm, Accounts.class);

		/*登録済のアカウント名と重複していないか確認*/
		if (userService.isUserExisting(accounts)) {
			bindingResult.rejectValue("user", "user.Alert");
		}
		
		/*登録済のメールアドレスと重複していないか確認*/
		if (userService.isEmailAddressExisting(accounts)) {
			bindingResult.rejectValue("emailAddress", "email_address.Alert");
		}
		
		if (bindingResult.hasErrors()) {
			return getEntryAccount(model,session,entryAccountForm);
		}
		
		accounts.setPass(passwordEncoder.encode(accounts.getPass()));

		userService.createAccount(accounts);
		
		return "redirect:login";
	}
}