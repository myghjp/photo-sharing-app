package portfolio.PhotoSharingApp.controller.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.service.group.GroupService;

@Controller
public class SelectGroupController {
	
	@Autowired
	private GroupService groupService;

	@GetMapping("/select-group")
	public String getSelectGroup(Model model) {
		
		/*※サービスからグループリストの呼び出し*/
		List<Groups> groupList = groupService.getGroupList();
		model.addAttribute("groupList", groupList);
		
		return "group/select-group";
	}
}