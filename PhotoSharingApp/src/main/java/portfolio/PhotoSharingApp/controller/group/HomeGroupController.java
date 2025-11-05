package portfolio.PhotoSharingApp.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class HomeGroupController {
	
	/*@Autowired
	private ModelMapper modelMapper;*/
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/home-group")
	public String getHomeGroup(Model model
			,@ModelAttribute("id")int id
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes) {
		
		Groups groups = groupService.getGroupsInfo(id);
		
		model.addAttribute("groups",groups);
		model.addAttribute("userId",loginUserDetails.getUserId());
		
		return "group/home-group";
	}
	
	@PostMapping("/home-group")
		public String postHomeGroup(@RequestParam("id")int id
				,RedirectAttributes redirectAttributes) {
			
			redirectAttributes.addFlashAttribute("id",id);
			
			return "redirect:delete-group";
		}
}
