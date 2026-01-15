package portfolio.PhotoSharingApp.controller.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import portfolio.PhotoSharingApp.form.account.UpdateAccountForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Controller
public class UpdateAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/update-account")
	public String getUpdateAccount(
			@ModelAttribute("updateAccountForm")UpdateAccountForm form
			) {
		
		return "account/update-account";
	}
	
	@PostMapping("/update-account")
	public String postUpdateAccount(
			HttpSession session
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("updateAccountForm") @Validated UpdateAccountForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) throws Exception {
		
		if (bindingResult.hasErrors()) {
			return getUpdateAccount(form);
		}
		
		Account account = modelMapper.map(form, Account.class);
		
		account.setId(user.getUserId());
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.edit(account);
		
		session.invalidate();
		
		return "redirect:login";
	}
}