package portfolio.PhotoSharingApp.controller.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import portfolio.PhotoSharingApp.service.account.AccountService;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class AddMemberController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/add-member")
	public String getAddMember(
			@ModelAttribute("addMemberForm")AddMemberForm form
			) {
		
		return "member/add-member";
	}
	
	@PostMapping("/add-member")
	public String postAddMember(
			@ModelAttribute("addMemberForm") @Validated AddMemberForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			,@ModelAttribute("group")Group group
			) {
		
		String email = form.getEmailAddress();
		
		/*このメールアドレスは登録されているかを確認*/
		if (accountService.isRegister(email)) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError");
		}
		/*このグループの管理者のメールアドレスではないかを確認*/
		else if (accountService.isOwner(group.getAccountId(),email)) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError3");
		}
		/*このグループに追加済のメールアドレスではないかを確認*/
		else if (memberService.isMember(email,group)) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError2");
		}
		
		if (bindingResult.hasErrors()) {
			return getAddMember(form);
		}
		
		Member member = new Member();
		
		member.setGroupId(group.getId());
		member.setAccountId(accountService.findById(email));
		
		memberService.insert(member);
		
		return "redirect:list-member";
	}
}