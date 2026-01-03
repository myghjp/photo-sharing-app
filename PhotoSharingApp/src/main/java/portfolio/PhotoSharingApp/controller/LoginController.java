package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}
	
	/*ーーやることーー*/
	
	/*役割毎のServiceクラスに変更*/
	/*xmlMapの書き直し*/
	
	/*全体の引数名をわかりやすく統一*/
	/*Exception名の変更*/
	
	/*staticからの画像削除を再確認*/
	/*第二引数Messageの再設定*/
	/*BootStrapの再確認*/
	
}