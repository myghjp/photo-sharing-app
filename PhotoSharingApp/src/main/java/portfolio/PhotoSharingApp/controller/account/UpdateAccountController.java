package portfolio.PhotoSharingApp.controller.account;

import jakarta.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@GetMapping("/update-account/{accountId}")
	public String getUpdateAccount(Model model
			,@PathVariable("accountId")int id
			,UpdateAccountForm updateAccountForm
			) {
		
		model.addAttribute("id",id);
		model.addAttribute("updateAccountForm", updateAccountForm);
		
		return "account/update-account";
	}
	
	@PostMapping("/update-account")
	public String postUpdateAccount(Model model
			,@RequestParam("id")int id
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute @Validated UpdateAccountForm updateAccountForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) throws Exception {
		
		Account account = modelMapper.map(updateAccountForm, Account.class);
		account.setId(id);
		
		/*↓getに*/
		
		/*idが自身のアカウントIdと同じかを確認*/
		/*if (accountService.isCurrentAccount(account.getId(),loginUserDetails.getAccountId())) {
			throw new AccessDeniedException("不正なIDです");
		}*/
	
		if (bindingResult.hasErrors()) {
			return getUpdateAccount(model,id,updateAccountForm);
		}
		
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.updateAccount(account);
		
		session.invalidate();
		
		return "redirect:login";
	}
}