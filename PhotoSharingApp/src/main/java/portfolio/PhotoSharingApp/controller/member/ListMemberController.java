package portfolio.PhotoSharingApp.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class ListMemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/list-member")
	public String getListMember(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("group")Group group
			,RedirectAttributes redirectAttributes
		) {

		List<Member> memberList = memberService.getMembersList(group.getId());
		model.addAttribute("memberList", memberList);

		/*自身は管理者*/
		if (group.getAccountId() == loginUserDetails.getAccountId()) {
			model.addAttribute("isAdmin", true);
		} else {
			String adminName = memberService.getAdminName(group.getId());
			model.addAttribute("adminName", adminName);
		}

		return "member/list-member";
	}
}