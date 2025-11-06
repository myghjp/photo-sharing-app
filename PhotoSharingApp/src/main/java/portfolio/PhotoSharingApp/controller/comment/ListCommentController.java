package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListCommentController {
		
	@GetMapping("/list-comment")
	public String getListComment() {
		
		return "comment/list-comment";
	}
	
	@PostMapping("/list-comment")
	public String postListComment() {
		return "redirect:list-comment";
	}

}