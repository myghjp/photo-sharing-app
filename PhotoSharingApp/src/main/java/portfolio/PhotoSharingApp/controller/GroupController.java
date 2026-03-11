package portfolio.PhotoSharingApp.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.CreateGroupForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.GroupService;

@Controller
@SessionAttributes(value = {"group"})
public class GroupController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private GroupService groupService;

	@ModelAttribute(value = "group")
	public Group group() {
		return new Group();
	}
	
	@GetMapping("/create-group")
	public String getCreateGroup(
			@ModelAttribute("createGroupForm")CreateGroupForm form
			) {
		
		return "group/create";
	}

	@GetMapping("/list-group")
	public String getListGroup(
			Model model
			,SessionStatus sessionStatus
			,@AuthenticationPrincipal LoginUserDetails user
			) {

		sessionStatus.setComplete();
	    
	    /*所属グループ情報を取得*/
		List<Group> groupList = groupService.getAffiliationGroupInfo(user.getUserId());
		model.addAttribute("groupList", groupList);

		return "group/list";
	}
	
	/*ーーーーーーーーーーーーーーーーーーーーーーーーーーー*/

	@PostMapping("/create-group")
	public String postCreateGroup(
			Model model
			,@AuthenticationPrincipal LoginUserDetails user
			,@ModelAttribute("createGroupForm") @Validated CreateGroupForm form
			,BindingResult bindingResult
			) {
		
		Group group = modelMapper.map(form, Group.class);

		/*グループ名と重複しているか*/
		if (groupService.isGroupNameDuplicate(group.getGroupName())){
			bindingResult.rejectValue("groupName","entryGroupNameError");
		}
		
		if (bindingResult.hasErrors()) {
			return getCreateGroup(form);
		}
		
		group.setAccountId(user.getUserId());
		groupService.create(group);
		
		return "redirect:list-group";
	}
	
	@PostMapping("/list-group")
	public String postListGroup(
			Model model
			,@RequestParam("id")int groupId
			,@AuthenticationPrincipal LoginUserDetails user
			) {

		Group group = groupService.findById(groupId);
		model.addAttribute("group",group);
		
		return "redirect:dashboard";
	}
	
	@PostMapping("/delete-group")
	public String postDeleteGroup(
			@RequestParam("id") int groupId
			,@AuthenticationPrincipal LoginUserDetails user
			) {
		
		/*グループ作成者か*/
		if (groupService.isGroupCreator(groupId,user.getUserId())) {
			throw new AccessDeniedException("不正なIDです");
		}

		groupService.delete(groupId);

		return "redirect:list-group";
	}
}