package portfolio.PhotoSharingApp.controller.members;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Accounts;
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
			,@ModelAttribute @Validated AddMembersForm addMembersForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
		) {
		
		Accounts accounts = modelMapper.map(addMembersForm, Accounts.class);
		
		/*アカウント登録されているメールアドレスか*/
		if (userService.isExistingAccountId(accounts)) {
			bindingResult.rejectValue("emailAddress", "unkownEmail.Alert");
		}
		
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		if (membersService.isExistingMembersId(accounts,groups)) {
			bindingResult.rejectValue("emailAddress", "testtesttest");
		}
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		if (bindingResult.hasErrors()) {
			return getAddMembers(model, addMembersForm);
		}
		/*ーーーーーーーーーーーーーーーーーー*/
		/*バリデーションを終えると
		メールアドレスを使用してアカウントIDを取得する*/
		int accountId = userService.selectAccountId(emailAddress);
		int groupId = groups.getId();
		
		Members members = new Members();
		members.setGroupId(groupId);
		members.setAccountId(accountId);
		/*membersのテーブルに
		sessionのグループのIDと
		取得したアカウントのIDを追加する(仮)*/
		membersService.insertMembers(members);
		return "redirect:list-members";
	}
}
