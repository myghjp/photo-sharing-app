package portfolio.PhotoSharingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryGroupController {
	
	@GetMapping("/entry-group")
	public String getEntryGroup() {
		
		/*※サービスからグループリストの呼び出し
		List<TodoItems> todoItemsList = todoService.getListAll();
		model.addAttribute("todoItemsList", todoItemsList);*/
		
		return "entry-group";
	}

}
