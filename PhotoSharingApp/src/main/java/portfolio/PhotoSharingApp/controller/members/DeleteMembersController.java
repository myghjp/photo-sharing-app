package portfolio.PhotoSharingApp.controller.members;

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

import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
@SessionAttributes(value = {"groups"})
public class DeleteMembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/delete-members/{id}")
	public String getDeleteMembers(Model model
			,@PathVariable("id")int memberId
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		/*※このグループの管理者でないならエラー*/
		/*このmembersIdのグループの管理者Idでなければエラー*/
		
		Members members = new Members();
		members.setId(memberId);
		
		if (membersService.isCurrentUser(members.getId()) != loginUserDetails.getUserId()) {
			throw new AccessDeniedException("不正なIDです");
		}
		/*ーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー*/
		
		/*グループ利用者IDとその名前を取得*/
		Members membersData = membersService.getMemberName(members.getId());
		model.addAttribute("membersData",membersData);
		
		return "members/delete-members";
	}
	
	@PostMapping("/delete-members")
	public String postDeleteMembers(Model model
			,@RequestParam("id")int memberId
			,RedirectAttributes redirectAttributes
			) {
		
		membersService.deleteMember(memberId);
		
		return "redirect:list-members";
	}
}