package portfolio.PhotoSharingApp.controller;

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
import portfolio.PhotoSharingApp.form.EntryAccountForm;
import portfolio.PhotoSharingApp.service.UserService;

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
		,EntryAccountForm entryAccountForm
	){
		model.addAttribute("entryAccountForm", entryAccountForm);
		
		/*ログインエラーメッセージの破棄*/
		/*session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");*/
		
		return "entry-account";
	}
	
	@PostMapping("/entry-account")
	public String postEntryAcount(Model model
			,@ModelAttribute @Validated EntryAccountForm entryAccountForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes) {
		
		Accounts accounts = modelMapper.map(entryAccountForm, Accounts.class);

		/*冗長*/
		if (userService.isExistingAccountsData1(accounts)) {
			bindingResult.rejectValue("user", "user.Alert");
		}
		if (userService.isExistingAccountsData2(accounts)) {
			bindingResult.rejectValue("email_address", "email_address.Alert");
		}
		
		if (bindingResult.hasErrors()) {
			return getEntryAccount(model, entryAccountForm);
		}
		
		accounts.setPass(passwordEncoder.encode(accounts.getPass()));

		userService.insertEntryAccount(accounts);
		
		return "redirect:login";
	}
}