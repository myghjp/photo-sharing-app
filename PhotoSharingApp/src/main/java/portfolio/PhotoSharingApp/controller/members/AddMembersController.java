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

@Controller
@SessionAttributes(value = {"groups"})
public class AddMembersController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MembersService membersService;
	
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
		
		/*メールアドレスを使用してアカウントIdが存在するかを確認*/
		if (membersService.isExistingAccountId(accounts)) {
			bindingResult.rejectValue("emailAddress", "unkownEmail.Alert");
		}
		
		/*このメールアドレスは、このグループ内にいる
		 * 利用者や管理者のアドレスが重複していないかを確認*/
		if (membersService.isExistingMembersId(accounts,groups)) {
			bindingResult.rejectValue("emailAddress", "email_address.Alert2");
		}
		
		if (bindingResult.hasErrors()) {
			return getAddMembers(model, addMembersForm);
		}
		
		Members members = new Members();
		
		members.setGroupId(groups.getId());
		members.setAccountId(membersService.selectAccountId(emailAddress));
		
		membersService.insertMembers(members);
		
		return "redirect:list-members";
	}
}