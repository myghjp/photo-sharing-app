package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListCommentController {
		
	@GetMapping("/list-comment")
	public String getListComment() {
		
		return "comment/list-comment";
	}

}