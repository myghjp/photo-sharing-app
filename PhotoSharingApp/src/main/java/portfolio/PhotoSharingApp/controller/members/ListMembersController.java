package portfolio.PhotoSharingApp.controller.members;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListMembersController {
	
	/*@Autowired
	private MembersService membersService;*/
	
	@GetMapping("/list-members")
	public String getListMembers() {
		
		/*List<Members> membersList = membersService.getMembersList(id);*/
		
		return "members/list-members";
	}

}
