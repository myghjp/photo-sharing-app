package portfolio.PhotoSharingApp.controller.account;

import jakarta.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
import portfolio.PhotoSharingApp.form.account.EditAccountForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.account.AccountService;

@Controller
public class EditAccountController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/edit-account/{userId}")
	public String getEditAccount(Model model
			,@PathVariable("userId")int id
			,EditAccountForm editAccountForm
			) {
		
		/*idをformで使える(Postも確認)*/
		model.addAttribute("id",id);
		model.addAttribute("editAccountForm", editAccountForm);
		
		return "account/edit-account";
	}
	
	@PostMapping("/edit-account")
	public String postEditAccount(Model model
			,@RequestParam("id")int id
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute @Validated EditAccountForm editAccountForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) throws Exception {
		
		Account account = modelMapper.map(editAccountForm, Account.class);
		account.setId(id);
		
		/*idが自身のアカウントIdと同じかを確認*/
		if (accountService.isCurrentUser(account.getId()) != loginUserDetails.getUserId()) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		if (bindingResult.hasErrors()) {
			return getEditAccount(model,id,editAccountForm);
		}
		
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.editAccount(account);
		
		session.invalidate();
		
		return "redirect:login";
	}
}