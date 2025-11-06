package portfolio.PhotoSharingApp.controller.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
public class DeleteMembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/delete-members")
	public String getDeleteMembers(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "members/delete-members";
	}
	
	@PostMapping("/delete-members")
	public String postDeleteMembers () {
		
		return "redirect:delete-members";
	}
	
	/*@PostMapping("/delete-members2")
	public String postDeleteMembers2 (@RequestParam("accountId")int accountId
			,RedirectAttributes redirectAttributes) {
	
		membersService.deleteMember(accountId);
		
		
		return "redirect:delete-members";
	}*/

}
