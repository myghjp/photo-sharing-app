package portfolio.PhotoSharingApp.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllAdvice {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model,HttpSession session) {

		model.addAttribute("error", "");
		model.addAttribute("message", "エラーが発生しました。");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		session.invalidate();
		
		return "error";
	}
}
