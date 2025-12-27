package portfolio.PhotoSharingApp.controller.members;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
@SessionAttributes(value = { "groups" })
public class ListMembersController {

	@Autowired
	private MembersService membersService;

	@GetMapping("/list-members")
	public String getListMembers(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("groups")Groups groups
			,RedirectAttributes redirectAttributes
		) {

		List<Members> membersList = membersService.getMembersList(groups.getId());
		model.addAttribute("membersList", membersList);

		/*自身は管理者*/
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("isAdmin", true);
		} else {
			String adminName = membersService.getAdminName(groups.getId());
			model.addAttribute("adminName", adminName);
		}

		return "members/list-members";
	}
}