package portfolio.PhotoSharingApp.controller.account;

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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getUpdateAccount(Model model
	/*,@PathVariable("accountId")int accountId*/
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,UpdateAccountForm updateAccountForm
			) {
		
		/*↓不要になる*/
		/*formのidも不要*/
		
		/*idが自身のアカウントIdと同じかを確認*/
		/*if (accountService.isCurrentAccount(accountId,loginUserDetails.getAccountId())) {
			throw new AccessDeniedException("不正なIDです");
		}*/
		
		model.addAttribute("id",loginUserDetails.getAccountId());
		model.addAttribute("updateAccountForm", updateAccountForm);
		
		return "account/update-account";
	}
	
	@PostMapping("/update-account")
	public String postUpdateAccount(Model model
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@RequestParam("id")int id
			,@ModelAttribute @Validated UpdateAccountForm updateAccountForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) throws Exception {
		
		if (bindingResult.hasErrors()) {
			return getUpdateAccount(model,loginUserDetails,updateAccountForm);
		}
		
		/*@RequestParamも不要*/
		
		Account account = modelMapper.map(updateAccountForm, Account.class);
		
		account.setId(id);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.updateAccount(account);
		
		session.invalidate();
		
		return "redirect:login";
	}
}