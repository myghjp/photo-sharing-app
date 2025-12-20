package portfolio.PhotoSharingApp.controller.group;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.security.LoginUserDetails;

@Controller
@SessionAttributes(value = {"groups"})
public class HomeGroupController {
	
	@GetMapping("/home-group")
	public String getHomeGroup(Model model
			,Groups groups
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes
			) {
		
		
		/*自身は管理者*/
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("isAdmin",true);
		}
		
		model.addAttribute("userId",loginUserDetails.getUserId());
		/*↑？*/
		
		return "group/home-group";
		
	}
}