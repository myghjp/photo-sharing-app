package portfolio.PhotoSharingApp.form.account;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdatePasswordForm {
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{8,12}$")
	private String password;
	
	private String passwordConfirmation;
	
	/*form内Message*/
	@AssertTrue(message = "テストメッセージ")
	public boolean isPasswordValid() {
		
		if (password == null || password.isEmpty()) {
			return true;
		}
		
		/*falseにMessage*/
		
		return password.equals(passwordConfirmation);
		
		/*controllerのresult？*/
	}

}