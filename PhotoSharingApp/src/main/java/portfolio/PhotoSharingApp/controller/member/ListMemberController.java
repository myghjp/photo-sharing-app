package portfolio.PhotoSharingApp.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.account.AccountService;
import portfolio.PhotoSharingApp.service.group.GroupService;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class ListMemberController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/list-member")
	public String getListMember(
			Model model
			,AddMemberForm form
			,@AuthenticationPrincipal LoginUserDetails user
			/*,RedirectAttributes redirectAttributes*/
			,@ModelAttribute("group")Group group
		) {
		
		model.addAttribute("addMemberForm", form);
		
		/*このグループ利用者のテーブル情報とアカウント名を取得*/
		List<Member> memberList = memberService.findAllById(group.getId());
		model.addAttribute("memberList", memberList);

		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin", true);
		} else {
			/*このグループの管理者名を取得*/
			String adminName = groupService.findByUsername(group.getId());
			model.addAttribute("adminName", adminName);
		}
		
		boolean isActive = true;
	    model.addAttribute("isActiveMember", isActive);

		return "member/list-member";
	}
	
	@PostMapping("/list-member")
	public String postListMember(
		Model model
		,@AuthenticationPrincipal LoginUserDetails user
		,@ModelAttribute("addMemberForm") @Validated AddMemberForm form
		,BindingResult bindingResult
		,RedirectAttributes redirectAttributes
		,@ModelAttribute("group")Group group
		) {
	
	/*↓postAddMember()と以下同じ*/
			
			
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
		return getListMember(model,form,user,group);
	}
	
	Member member = new Member();
	
	member.setGroupId(group.getId());
	member.setAccountId(accountService.findById(email));
	
	memberService.insert(member);
	
	return "redirect:list-member";
	
	}
}