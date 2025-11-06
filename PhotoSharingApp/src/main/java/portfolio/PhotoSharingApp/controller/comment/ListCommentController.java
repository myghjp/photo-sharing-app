package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ListCommentController {
		
	@GetMapping("/list-comment")
	public String getListComment(Model model
			,RedirectAttributes redirectAttributes) {
		
		return "comment/list-comment";
	}
	
	@PostMapping("/list-comment")
	public String postListComment(@RequestParam("id")int id
				,RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("id",id);
		
		return "redirect:list-comment";
	}
}