package portfolio.PhotoSharingApp.controller.member;

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

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.form.member.AddMemberForm;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class AddMemberController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/add-member")
	public String getAddMember(Model model
			,AddMemberForm form
			) {
			
		model.addAttribute("addMembersForm",form);
		
		return "member/add-member";
	}
	
	@PostMapping("/add-member")
	public String postAddMember(Model model
			,@RequestParam("emailAddress") String email
			,@ModelAttribute("group")Group group
			,@ModelAttribute @Validated AddMemberForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		Account account = modelMapper.map(form, Account.class);
		
		/*メールアドレスを使用してアカウントIdが存在するかを確認*/
		if (memberService.isExistingAccountId(account)) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError");
		}
		
		/*このメールアドレスは、このグループ内にいる
		 * 利用者や管理者のアドレスが重複していないかを確認*/
		if (memberService.isExistingMembersId(account,group)) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError2");
		}
		
		if (bindingResult.hasErrors()) {
			return getAddMember(model, form);
		}
		
		Member member = new Member();
		
		member.setGroupId(group.getId());
		member.setAccountId(memberService.selectAccountId(email));
		
		memberService.insertMembers(member);
		
		return "redirect:list-member";
	}
}