package portfolio.PhotoSharingApp.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portfolio.PhotoSharingApp.service.comment.CommentService;

@Controller
public class DeleteCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/delete-comment/{id}")
	public String getDeleteComment(Model model
			/*,DeleteCommentForm deleteCommentForm*/
			,@PathVariable("id") int id) {

				
				/*TodoItems todoItems = todoService.getTodoItems(id);
				model.addAttribute("todoItems", todoItems);*/
		
		model.addAttribute("id", id);

		return "comment/delete-comment";
	}
	
	@PostMapping("/delete-comment")
	public String getDeleteComment(Model model
			,@RequestParam("id") int id
			,RedirectAttributes redirectAttributes
		) {
	
		/*コメントの削除*/
		commentService.deleteComment(id);
	
		return "redirect:list-comment";
	}
	

}
