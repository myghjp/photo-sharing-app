package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ListCommentController {
	
	@GetMapping("/list-comment/{id}")
	public String getListComment(Model model
			,@PathVariable("id")int id
		) {
		
			/*model.addAttribute("id",id);*/
		
		return "comment/list-comment";
	}
	
	/*@PostMapping("/list-comment")
	public String postListComment(@RequestParam("id")int id
				,RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("id",id);
		
		return "redirect:list-comment";
	}*/
}