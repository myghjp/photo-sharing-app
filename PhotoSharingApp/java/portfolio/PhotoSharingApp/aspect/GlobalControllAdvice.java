package portfolio.PhotoSharingApp.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
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
	
	/*SessionAttributesの変数に値が存在しない場合はグループ選択画面にリダイレクト*/
	@ExceptionHandler(IllegalStateException.class)
	public String handleIllegalStateException(IllegalStateException e) {
		
        if (e.getMessage().contains("Expected session attribute")) {
			return "redirect:select-group";
        }
        throw e;
    }
	
	@ModelAttribute
	public void currentURL(Model model,HttpServletRequest request) {
		model.addAttribute("currentURL", request.getServletPath());
	}
}