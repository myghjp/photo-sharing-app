package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeleteCommentController {
	
	@GetMapping("/delete-comment")
	public String getDeleteComment() {
		
		return "comment/delete-comment";
	}

}
