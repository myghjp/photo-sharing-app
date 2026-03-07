package portfolio.PhotoSharingApp.controller.member;

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
import portfolio.PhotoSharingApp.form.member.AddMemberForm;
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
		
		/*自身がグループの管理者であるかを確認*/
		if (group.getAccountId() == user.getUserId()) {
			model.addAttribute("isAdmin", true);
		}
		
		/*このグループ管理者のアカウント名とメールアドレスを取得*/
		Group adminInfo = groupService.getGroupAdminInfo(group.getId());
		model.addAttribute("adminInfo", adminInfo);
		
		/*このグループ利用者のテーブル情報とアカウント名とメールアドレスを取得*/
		List<Member> memberList = memberService.getMemberList(group.getId());
		model.addAttribute("memberList", memberList);
		
		/*このグループのメンバーの数を取得*/
		int countMembers = memberService.getCountMembers(group.getId());
		model.addAttribute("countMembers", countMembers);
		
		return "member/list-member";
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
		
		/*登録済のメールアドレスと重複していないかを確認*/
		if (accountService.existsEmail(email)) {
			bindingResult.rejectValue("emailAddress", "entryAccountEmailError");
		}
		/*このグループの管理者のメールアドレスではないかを確認*/
		else if (accountService.hasGroupOwnerEmail(group.getAccountId(),email)) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError3");
		}
		/*このグループに追加済のメールアドレスではないかを確認*/
		else if (memberService.hasEmail(email,group)) {
			bindingResult.rejectValue("emailAddress", "addMemberEmailError2");
		}
		
		if (bindingResult.hasErrors()) {
			return getListMember(model,form,user,group);
		}
		
		Member member = new Member();
		member.setGroupId(group.getId());
		
		Account account = accountService.findByEmail(email);
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
		
		/*このグループ利用者はグループの管理者であるかを確認*/
		if (memberService.hasGroupAdmin(memberId,user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		memberService.delete(memberId);

		return "redirect:list-member";
	}
}