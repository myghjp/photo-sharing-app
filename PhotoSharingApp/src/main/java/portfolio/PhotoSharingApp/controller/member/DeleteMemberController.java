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
	public String getDeleteMember(Model model
			,@PathVariable("id")int id
			,@AuthenticationPrincipal LoginUserDetails user
			) {
		
		Member member = new Member();
		member.setId(id);
		
		/*このグループの管理者であるかを確認*/
		if (memberService.isFindGroupAdmin(member.getId(),user.getAccountId())) {
			throw new AccessDeniedException("不正なIDです");
		}
		
		/*グループ利用者IDとその名前を取得*/
		Member memberData = memberService.getMemberName(member.getId());
		model.addAttribute("memberData",memberData);
		
		return "member/delete-member";
	}
	
	@PostMapping("/delete-member")
	public String postDeleteMember(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		memberService.deleteMember(id);
		
		return "redirect:list-member";
	}
}