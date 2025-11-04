package portfolio.PhotoSharingApp.controller.group;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.form.group.SelectGroupForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
@Slf4j
public class HomeGroupController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/home-group")
	public String getHomeGroup(Model model
			,@ModelAttribute SelectGroupForm selectGroupForm
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes) {
		
		Groups groupsInfo = modelMapper.map(selectGroupForm, Groups.class);
		
		Groups groups = groupService.getGroupsInfo(groupsInfo);
		
		log.info(groups.toString());
		
		model.addAttribute("groups",groups);
		model.addAttribute("userId",loginUserDetails.getUserId());
		
		/*id == accountId なら管理者*/
		
		
		return "group/home-group";
	}

}
