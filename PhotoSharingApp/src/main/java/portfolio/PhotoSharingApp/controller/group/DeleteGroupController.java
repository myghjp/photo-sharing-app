package portfolio.PhotoSharingApp.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteGroupController {
	
	@GetMapping("/delete-group")
	public String getDeleteGroup() {
		
		return "group/delete-group";
	}

}
