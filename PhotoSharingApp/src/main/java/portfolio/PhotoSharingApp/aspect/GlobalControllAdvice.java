package portfolio.PhotoSharingApp.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllAdvice {

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		model.addAttribute("error", "");
		model.addAttribute("message", "エラーが発生しました。");
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}
}
