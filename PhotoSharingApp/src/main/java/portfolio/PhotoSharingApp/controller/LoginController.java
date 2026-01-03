package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}
	
	/*第二引数Messageの再設定*/
	
	
	/*staticからの画像削除を再確認*/
	/*BootStrapの再確認*/
	
}