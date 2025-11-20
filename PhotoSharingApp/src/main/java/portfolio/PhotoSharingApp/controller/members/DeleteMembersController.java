package portfolio.PhotoSharingApp.controller.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
@SessionAttributes(value = {"groups"})
public class DeleteMembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/delete-members")
	public String getDeleteMembers(
			RedirectAttributes redirectAttributes
			) {
		
		return "members/delete-members";
	}
	
	@PostMapping("/delete-members")
	public String postDeleteMembers(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		membersService.deleteMember(id);
		
		return "redirect:list-members";
	}


}
