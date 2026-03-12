package portfolio.PhotoSharingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import portfolio.PhotoSharingApp.entity.Account;
import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.form.AddMemberForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.AccountService;
import portfolio.PhotoSharingApp.service.GroupService;
import portfolio.PhotoSharingApp.service.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class MemberController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/list-member")
	public String getListMember(
			Model model
			,@ModelAttribute("addMemberForm")AddMemberForm form
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("group")Group group
		) {
		
		/*グループ管理者か*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin", true);
		}
		
		/*グループ管理者情報を取得*/
		Group adminInfo = groupService.getAdminInfo(group.getId());
		model.addAttribute("adminInfo", adminInfo);
		
		/*グループメンバーの情報を取得*/
		List<Member> memberList = memberService.getGroupMemberInfo(group.getId());
		model.addAttribute("memberList", memberList);
		
		return "member/list";
	}
	
	@PostMapping("/list-member")
	public String postListMember(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("addMemberForm")@Validated AddMemberForm form
			,BindingResult bindingResult
			,@ModelAttribute("group")Group group
		) {
		
		String email = form.getEmailAddress();
		Account account = accountService.findByEmail(email);
		
		/*メールアドレスは登録されているか*/
		if (account == null) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError");
		}
		/*グループ管理者のメールアドレスか*/
		else if (account.getEmailAddress().equals(group.getAccount().getEmailAddress())) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError2");
		}
		/*メールアドレスは追加済か*/
		else if (memberService.isEmailAddressAdded(account.getEmailAddress(),group)) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError3");
		}
		
		if (bindingResult.hasErrors()) {
			return getListMember(model,form,user,group);
		}
		
		Member member = new Member();
		member.setGroupId(group.getId());
		member.setAccountId(account.getId());

		memberService.insert(member);
		
		return "redirect:list-member";
	
	}
	
	@PostMapping("/delete-member")
	public String postDeleteMember(
			Model model
			,@RequestParam("id") int memberId
			,@AuthenticationPrincipal LoginUserDetails user
			) {
		
		/*グループ管理者か*/
		if (memberService.isGroupAdmin(memberId,user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		memberService.delete(memberId);

		return "redirect:list-member";
	}
}