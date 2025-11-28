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
import portfolio.PhotoSharingApp.form.group.EntryGroupForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
@Slf4j
public class EntryGroupController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GroupService groupService;
	
	/*@Autowired
	private MembersService membersService;*/
	
	@GetMapping("/entry-group")
	public String getEntryGroup(Model model
			,EntryGroupForm entryGroupForm
			) {
		
		model.addAttribute("entryGroupForm", entryGroupForm);
		return "group/entry-group";
	}

	@PostMapping("/entry-group")
	public String postEntryGroup(Model model
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,@ModelAttribute @Validated EntryGroupForm entryGroupForm
			,BindingResult bindingResult
			,RedirectAttributes redirectAttributes
			) {
		
		Groups groups = modelMapper.map(entryGroupForm, Groups.class);

		/*重複グループ名*/
		if (groupService.isExistingGroupsData(groups)){
			bindingResult.rejectValue("groupName","group.Alert");
		}
		
		if (bindingResult.hasErrors()) {
			return getEntryGroup(model, entryGroupForm);
		}
		
		/*ーーーーーーーーーーーーーーー*/
		/*グループを登録*/
		groups.setAccountId(loginUserDetails.getUserId());
		groupService.entryGroup(groups);
		
		
		/*ーーー[管理者を利用者に追加しない]以下作り直しーーー*/
		
		/*ユーザIDからグループIDを取得*/
		
		/*↓完全に間違い*/
		/*※二件以上返却しているエラー↓*/
		/*int groupId = groupService.getByGroupId(loginUserDetails.getUserId());*/
		
		/*グループIDとアカウントIDをメンバに登録*/
		/*Members members = new Members();
		members.setGroupId(groupId);
		members.setAccountId(loginUserDetails.getUserId());
		log.info(members.toString());*/
		
		/*membersService.insertMembers(members);*/
		
		return "redirect:select-group";
	}
}
