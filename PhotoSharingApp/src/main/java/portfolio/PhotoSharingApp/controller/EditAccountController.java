package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import portfolio.PhotoSharingApp.form.EditAccountForm;

@Controller
public class EditAccountController {

	@GetMapping("/edit-account")
	public String getEditAccount(Model model
			,EditAccountForm editAccountForm){
		return "edit-account";
	}
}
