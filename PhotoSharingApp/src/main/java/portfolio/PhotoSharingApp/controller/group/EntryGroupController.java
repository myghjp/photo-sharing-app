package portfolio.PhotoSharingApp.controller.group;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;
import portfolio.PhotoSharingApp.form.group.EntryGroupForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;
import portfolio.PhotoSharingApp.service.members.MembersService;

@Controller
@Slf4j
public class EntryGroupController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/entry-group")
	public String getEntryGroup(Model model
			,EntryGroupForm entryGroupForm) {
		
		model.addAttribute("entryGroupForm", entryGroupForm);
		return "group/entry-group";
	}

	@PostMapping("/entry-group")
	public String postEntryGroup(Model model
			,Members members
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute @Validated EntryGroupForm entryGroupForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes) {
		
		Groups groups = modelMapper.map(entryGroupForm, Groups.class);

		if (groupService.isExistingGroupsData(groups)){
			bindingResult.rejectValue("groupName","group.Alert");
		}
		
		if (bindingResult.hasErrors()) {
			return getEntryGroup(model, entryGroupForm);
		}
		
		groups.setAccountId(loginUserDetails.getUserId());
		
		log.info(groups.toString());

		/*ーーーーーーーーーーーーーーー*/
		/*グループを登録*/
		groupService.insertEntryGroup(groups);
		/*ユーザIDからグループIDを取得*/
		int groupId = groupService.selectByGroupId(loginUserDetails.getUserId());
		
		/*ユーザIDとグループIDをメンバに登録*/
		members.setGroupId(groupId);
		members.setAccountId(loginUserDetails.getUserId());
		membersService.insertMembers(members);
		
		return "redirect:select-group";
	}
}
