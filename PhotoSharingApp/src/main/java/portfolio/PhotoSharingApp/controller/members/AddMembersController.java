package portfolio.PhotoSharingApp.controller.members;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.form.members.AddMembersForm;

@Controller
public class AddMembersController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	/*@Autowired
	private UserService userService;*/
	
	@GetMapping("/add-members/{id}")
	public String getAddMembers(Model model
			/*,@PathVariable("id")int id*/
			,AddMembersForm addMembersForm
		) {
		
		model.addAttribute("addMembersForm",addMembersForm);
		return "members/add-members";
	}
	
	@PostMapping("/add-members")
	public String postAddMembers(Model model
			,@ModelAttribute AddMembersForm addMembersForm
			,RedirectAttributes redirectAttributes
		) {
		
		Members members = modelMapper.map(addMembersForm, Members.class);
		
		
		
		/*メールアドレスの変数↑*/
		/*ーーーーーーーーーーーーーーーーーー*/
		/*入力されている*/
		/*メールアドレスの形式である*/
		/*ーーーーーーーーーーーーーーーーーー*/
		/*if (userService.isExistingAccountsData2(accounts)) {
			bindingResult.rejectValue("email_address", "email_address.Alert");
		}*/
		/*追加するアドレスがアカウント登録されていること,*/
		/*※※追加済の同じグループのメンバのアドレスと重複していないこと,*/
		/*ーーーーーーーーーーーーーーーーーー*/
		
		/*このグループのIDは？*/
		/*メールアドレスからアカウントIDを取得*/
		
		/*ーーーーーーーーーーーーーーーーーー*/
		/*バリデーションを終えると
		membersのテーブルに
		グループのIDと
		アカウントのIDを追加する*/
		return "redirect:list-members";
	}
}
