package portfolio.PhotoSharingApp.controller.group;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeGroupController {
	
	@GetMapping("/home-group")
	public String getHomeGroup() {
		
		return "group/home-group";
	}

}
