package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteAccountController {

	@GetMapping("/delete-account")
	public String getDeleteAccount(){
		return "delete-account";
	}
}