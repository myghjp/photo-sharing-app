package portfolio.PhotoSharingApp.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SelectGroupController {

	@GetMapping("/select-group")
	public String getLogin() {
		
		/*※サービスからグループリストの呼び出し
		List<TodoItems> todoItemsList = todoService.getListAll();
		model.addAttribute("todoItemsList", todoItemsList);*/
		
		return "group/select-group";
	}
}