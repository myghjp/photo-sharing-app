package portfolio.PhotoSharingApp.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteGroupController {

	@GetMapping("/delete-group/{id}")
	public String getDeleteGroup(Model model, @PathVariable("id") int id) {

		/*idとname*/
		/*idからname*/
		
		model.addAttribute(id);

		return "group/delete-group";
	}

	/*@PostMapping("/delete-group")
	public String postDeleteGroup(
			RedirectAttributes redirectAttributes) {
		
		
		
		idのみ
		
		return "redirect:group/delete-group";
	}*/

}
