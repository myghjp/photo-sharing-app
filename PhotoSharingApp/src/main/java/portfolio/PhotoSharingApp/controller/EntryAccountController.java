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
		return "entry-account";
	}
	
	@PostMapping("/entry-account")
	public String postEntryAcount(Model model
			,@ModelAttribute @Validated EntryAccountForm entryAccountForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes) {
		
		
		/*カスタム重複チェックが必要*/
		
		/*UsersData usersData = modelMapper.map(form, UsersData.class);

		if (userService.isExistingUserName(usersData)) {
			bindingResult.rejectValue("userName", "userName.search");
		}*/
		
		
		if (bindingResult.hasErrors()) {
			return getEntryAccount(model, entryAccountForm);
		}
		
		Accounts accounts = modelMapper.map(entryAccountForm, Accounts.class);
		
		accounts.setPass(passwordEncoder.encode(accounts.getPass()));

		userService.insertEntryAccount(accounts);
		
		return "redirect:login";
	}
}