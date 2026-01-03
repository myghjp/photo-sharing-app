package portfolio.PhotoSharingApp.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Member;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.member.MemberService;

@Controller
@SessionAttributes(value = {"group"})
public class DeleteMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/delete-member/{id}")
	public String getDeleteMembers(Model model
			,@PathVariable("id")int memberId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		Member member = new Member();
		member.setId(memberId);
		
		/*※このグループの管理者でないならエラー*/
		if (memberService.isCurrentUser(member.getId()) != loginUserDetails.getUserId()) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		/*グループ利用者IDとその名前を取得*/
		Member memberData = memberService.getMemberName(member.getId());
		model.addAttribute("memberData",memberData);
		
		return "member/delete-member";
	}
	
	@PostMapping("/delete-member")
	public String postDeleteMembers(Model model
			,@RequestParam("id")int memberId
			,RedirectAttributes redirectAttributes
			) {
		
		memberService.deleteMember(memberId);
		
		return "redirect:list-member";
	}
}