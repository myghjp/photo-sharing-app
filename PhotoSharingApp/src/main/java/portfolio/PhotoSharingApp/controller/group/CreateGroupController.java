package portfolio.PhotoSharingApp.controller.group;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.group.CreateGroupForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class CreateGroupController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GroupService groupService;
	
	@GetMapping("/create-group")
	public String getCreateGroup(
			@ModelAttribute("createGroupForm")CreateGroupForm form
			) {
		
		return "group/create-group";
	}

	@PostMapping("/create-group")
	public String postCreateGroup(
			@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("createGroupForm") @Validated CreateGroupForm form
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		Group group = modelMapper.map(form, Group.class);

		/*登録済のグループ名と重複していないかを確認*/
		if (groupService.existsByGroupName(group.getGroupName())){
			bindingResult.rejectValue("groupName","entryGroupNameError");
		}
		
		if (bindingResult.hasErrors()) {
			return getCreateGroup(form);
		}
		
		group.setAccountId(user.getUserId());
		groupService.create(group);
		
		return "redirect:select-group";
	}
}