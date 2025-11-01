package portfolio.PhotoSharingApp.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeGroupController {
	
	@GetMapping("/home-group")
	public String getHomeGroup(@ModelAttribute String ggg
			,RedirectAttributes redirectAttributes) {
		
		return "group/home-group";
	}

}
