package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SelectGroupController {

	@GetMapping("/select-group")
	public String getLogin() {
		return "select-group";
	}
}