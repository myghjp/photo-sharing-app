package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
/*@SessionAttributes(value = { "groups"})*/
public class ListMembersController {
	
	/*@Autowired
	private MembersService membersService;*/
	
	@GetMapping("/list-members")
	public String getListMembers(Model model
			) {
		
		int accountId = 1;
		model.addAttribute("accountId",accountId);
		
		/*int groupId = groups.getId();
		List<Members> membersList = membersService.getMembersList(groupId);
		model.addAttribute("membersList",membersList);*/
		
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
