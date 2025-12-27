package portfolio.PhotoSharingApp.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllAdvice {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Model model,Exception e,HttpSession session) {

		model.addAttribute("error", "");
		model.addAttribute("message", "エラーが発生しました。");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		session.invalidate();
		
		e.printStackTrace();
		
		return "common/error";
	}
}