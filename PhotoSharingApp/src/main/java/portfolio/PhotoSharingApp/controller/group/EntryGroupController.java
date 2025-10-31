package portfolio.PhotoSharingApp.controller.group;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
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
			,EntryGroupForm entryGroupForm) {
		
		model.addAttribute("entryGroupForm", entryGroupForm);
		
		return "group/entry-group";
	}

	/*@Validated*/
	@PostMapping("/entry-group")
	public String postEntryAcount(Model model
			,@ModelAttribute EntryGroupForm entryGroupForm
			/*,BindingResult bindingResult*/
			,@AuthenticationPrincipal LoginUserDetails loginUserDetails
			,RedirectAttributes redirectAttributes) {
		
		Groups groups = modelMapper.map(entryGroupForm, Groups.class);

		
		/*if (userService.isExistingAccountsData1(accounts)) {
			bindingResult.rejectValue("user", "user.Alert");
		}*/
		
		
		/*if (bindingResult.hasErrors()) {
			return getEntryAccount(model, entryAccountForm);
		}*/
		
		
		groups.setAccount_id(loginUserDetails.getUserId());
		

		groupService.insertEntryGroup(groups);
		
		return "redirect:select-group";
	}
}
