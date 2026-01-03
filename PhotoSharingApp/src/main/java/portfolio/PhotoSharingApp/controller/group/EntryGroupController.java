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

import portfolio.PhotoSharingApp.entity.Group;
import portfolio.PhotoSharingApp.form.group.EntryGroupForm;
import portfolio.PhotoSharingApp.security.LoginUserDetails;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class EntryGroupController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GroupService groupService;
	
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
		
		Group group = modelMapper.map(entryGroupForm, Group.class);

		/*登録済のグループ名の重複確認*/
		if (groupService.isExistingGroupsData(group)){
			bindingResult.rejectValue("groupName","entryGroupNameError");
		}
		
		if (bindingResult.hasErrors()) {
			return getEntryGroup(model, entryGroupForm);
		}
		
		group.setAccountId(loginUserDetails.getAccountId());
		groupService.entryGroup(group);
		
		return "redirect:select-group";
	}
}