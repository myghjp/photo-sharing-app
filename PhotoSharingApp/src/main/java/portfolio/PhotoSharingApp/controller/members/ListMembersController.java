package portfolio.PhotoSharingApp.controller.members;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
@SessionAttributes(value = { "groups"})
public class ListMembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/list-members")
	public String getListMembers(Model model
			,Groups groups
			) {
		
		int groupId = groups.getId();
		List<Members> membersList = membersService.getMembersList(groupId);
		model.addAttribute("membersList",membersList);
		
		return "members/list-members";
	}
	
	@PostMapping("/list-members")
	public String postListMembers(Model model
			,@RequestParam("accountId") int accountId
			,RedirectAttributes redirectAttributes
			) {
		
		model.addAttribute("accountId",accountId);
		
		return "redirect:delete-members";
	}

}
