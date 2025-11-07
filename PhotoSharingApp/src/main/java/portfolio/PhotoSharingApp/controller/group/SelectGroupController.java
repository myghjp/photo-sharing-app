package portfolio.PhotoSharingApp.controller.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.form.group.SelectGroupForm;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class SelectGroupController {
	
	@Autowired
	private GroupService groupService;

	@GetMapping("/select-group")
	public String getSelectGroup(Model model
			,SelectGroupForm selectGroupForm
		) {
		
		/*ログインしているユーザ が 参加しているグループを表示(未定)*/
		List<Groups> groupList = groupService.getGroupList();
		model.addAttribute("groupList", groupList);
		
		
		return "group/select-group";
	}
	
	@PostMapping("/select-group")
	public String postSelectGroup(
			Model model
			,@RequestParam("id")int id
			,RedirectAttributes redirectAttributes
		) {
		
		redirectAttributes.addFlashAttribute("id",id);
		
		return "redirect:home-group";
	}
}