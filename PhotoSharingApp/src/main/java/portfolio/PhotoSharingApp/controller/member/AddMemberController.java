package portfolio.PhotoSharingApp.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.form.member.AddMemberForm;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class AddMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/add-member")
	public String getAddMember(Model model
			,AddMemberForm form
			) {
			
		model.addAttribute("addMemberForm",form);
		
		return "member/add-member";
	}
	
	@PostMapping("/add-member")
	public String postAddMember(Model model
			,@ModelAttribute("addMemberForm") @Validated AddMemberForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			) {
		
		String email = form.getEmailAddress();
		
		/*アカウントIdが存在するかを確認*/
		if (memberService.isExistingAccountId(email)) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError");
		}
		
		/*このグループ内にいる利用者のアドレスが重複していないかを確認*/
		if (memberService.isExistingMembersId(email,group)) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError2");
		}
		
		/*このグループの管理者のメールアドレスならエラー*/
		if (memberService.is(group.getAccountId(),email)) {
			bindingResult.rejectValue("emailAddress", "addMembersEmailError3");
		}
		
		if (bindingResult.hasErrors()) {
			return getAddMember(model, form);
		}
		
		Member member = new Member();
		
		member.setGroupId(group.getId());
		member.setAccountId(memberService.selectAccountId(email));
		
		memberService.insertMember(member);
		
		return "redirect:list-member";
	}
}