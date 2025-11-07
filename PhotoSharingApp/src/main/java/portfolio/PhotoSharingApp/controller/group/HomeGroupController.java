package portfolio.PhotoSharingApp.controller.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class HomeGroupController {
	
	@Autowired
	private GroupService groupService;
	
	/*@GetMapping("/home-group")
	public String getHomeGroup(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute("id")int id
			,RedirectAttributes redirectAttributes) {
		
		Groups groups = groupService.getGroupsData(id);
		
		model.addAttribute("groups",groups);
		model.addAttribute("userId",loginUserDetails.getUserId());
		
		return "group/home-group";
	}*/
	
	@GetMapping("/home-group")
	public String getHomeGroup(Model model
			,@ModelAttribute("id")int id
			,RedirectAttributes redirectAttributes
			,HttpSession session
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			) {
		
		Groups groups = groupService.getGroupsData(id);
		
		/*model.addAttribute("groups",groups);*/
		session.setAttribute("groups",groups);
		
		/*if*/
		/*model.addAttribute("userId",loginUserDetails.getUserId());*/
		
		return "group/home-group";
	}
}
