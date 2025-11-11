package portfolio.PhotoSharingApp.controller.members;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.form.members.AddMembersForm;
import portfolio.PhotoSharingApp.service.members.MembersService;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
@SessionAttributes(value = {"groups"})
public class AddMembersController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/add-members")
	public String getAddMembers(Model model
			,AddMembersForm addMembersForm
		) {
		
		model.addAttribute("addMembersForm",addMembersForm);
		return "members/add-members";
	}
	
	@PostMapping("/add-members")
	public String postAddMembers(Model model
			,Groups groups
			,@RequestParam("emailAddress") String emailAddress
			,@ModelAttribute AddMembersForm addMembersForm
			/*,BindingResult bindingResult*/
			,RedirectAttributes redirectAttributes
		) {
		
		/*変換*/
		Members members = modelMapper.map(addMembersForm, Members.class);
		
		/*※メールアドレスを登録するわけではない*/
		
		/*ーーーーーーーーーーーーーーーーーー*/
		/*if (membersService.isExistingMembersData(emailAddress)) {
			bindingResult.rejectValue("email_address", "email_address.Alert");
		}*/
		/*追加するアドレスがアカウント登録されていること(アカウントIDが存在するか)*/
		/*メールアドレスでIDが存在するか*/
		
		/*追加済の同じグループのメンバのアドレスと重複していないこと
		 * (メンバリストのアカウントIDとアカウントテーブルIDの比較？？？)*/
		/*ーーーーーーーーーーーーーーーーーー*/
		/*if (bindingResult.hasErrors()) {
			return getAddMembers(model, addMembersForm);
		}*/
		/*入力されている*/
		/*メールアドレスの形式である*/
		/*ーーーーーーーーーーーーーーーーーー*/
		/*バリデーションを終えると
		メールアドレスを使用してアカウントIDを取得する(バインドとまとめる？)
		*/
		int accountId = userService.selectAccountId(emailAddress);
		
		int groupId = groups.getId();
		
		members.setGroupId(groupId);
		members.setAccountId(accountId);
		/*membersのテーブルに
		sessionのグループのIDと
		取得したアカウントのIDを追加する(仮)*/
		
		membersService.insertMembers(members);
		
		
		
		/*ーーーーーーーーーーーーーーーーーー*/
		return "redirect:list-members";
	}
}
