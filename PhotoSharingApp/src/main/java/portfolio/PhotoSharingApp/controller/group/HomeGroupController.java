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
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,Groups groups
			,RedirectAttributes redirectAttributes) {
		
		/*管理者*/
		/*もしグループ内のアカウントIDとユーザ自身のIDが一致すれば管理者である*/
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("admin",true);
		}
		
		model.addAttribute("userId",loginUserDetails.getUserId());
		return "group/home-group";
		
		
		/*ーー↓AからEまでーーーーーーーーーーーー*/
		/* C-3,アカウント削除のバインド*/
		
		
		/*ーーーーーーーーーーーーーーーーーーーー*/
	}
}