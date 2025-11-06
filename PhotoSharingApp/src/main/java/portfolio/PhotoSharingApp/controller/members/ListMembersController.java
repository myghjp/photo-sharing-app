package portfolio.PhotoSharingApp.controller.members;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
@Slf4j
public class ListMembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/list-members")
	public String getListMembers(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "members/list-members";
	}
	
	@PostMapping("/list-members")
	public String postListMembers(@RequestParam("id")int id
				,RedirectAttributes redirectAttributes) {
		
		List<Members> membersList = membersService.getMembersList(id);
		
		log.info(membersList.toString());
		
		redirectAttributes.addFlashAttribute("membersList",membersList);
		redirectAttributes.addFlashAttribute("id",id);
		
		return "redirect:list-members";
	}
}
