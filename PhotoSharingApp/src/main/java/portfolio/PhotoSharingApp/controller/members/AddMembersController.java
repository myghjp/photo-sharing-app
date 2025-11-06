package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMembersController {
	
	@GetMapping("/add-members")
	public String getAddMembers(Model model) {
		
		/*メンバテーブル(groupId)とグループテーブルの(Id)はJOIN*/
		
		/*メールアドレスはアカウントのテーブル*/
		/*メンバテーブル(accountId)とアカウントのテーブル(Id)もJOIN？*/
		
		return "members/add-members";
	}
	
	/*Post*/
	
	/*必要↓*/
	/*どのアカウントID
	どのグループID*/
	
	/*メールアドレスの形式である,
	追加するアドレスがアカウント登録されていること,
	追加済の同じグループのメンバのアドレスと重複していないこと,
	入力されている*/
}
