package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	/*Password変更テキスト*/
	/*group数が0の時*/
	/*dashboardの数sql*/
	/*画面設計書修正の後ファイル名とclass名変更*/
	
}