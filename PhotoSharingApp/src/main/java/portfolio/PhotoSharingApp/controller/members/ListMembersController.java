package portfolio.PhotoSharingApp.controller.members;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.members.MembersService;
import portfolio.PhotoSharingApp.service.user.UserService;

@Controller
@SessionAttributes(value = {"groups"})
/*@Slf4j*/
public class ListMembersController {
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/list-members")
	public String getListMembers(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,Groups groups
			) {
		
		List<Members> membersList = membersService.getMembersList(groups.getId());
		
		/*log.info(membersList.toString());*/
		
		model.addAttribute("membersList",membersList);
		
		/*もしグループ内のアカウントIDとユーザ自身のIDが一致すれば管理者である*/
		if (groups.getAccountId() == loginUserDetails.getUserId()) {
			model.addAttribute("isAdmin",true);
		}else {
			/*グループのIDからアカウントIDを紐づけて管理者名を取得*/
			String adminName = userService.getAdminName(groups.getId());
			model.addAttribute("adminName",adminName);
		}
		
		return "members/list-members";
	}
	
	@PostMapping("/list-members")
	public String postListMembers(Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
			) {
		
		/*idからuserNameを取得*/
		Accounts accounts = userService.selectByUserName(id);
		
		redirectAttributes.addFlashAttribute("accounts",accounts);
		
		return "redirect:delete-members";
	}

}
