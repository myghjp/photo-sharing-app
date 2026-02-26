package portfolio.PhotoSharingApp.form.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdatePasswordForm {

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{8,12}$")
	private String password;

	private String passwordConfirmation;

	public boolean isPasswordValid() {
		
		if (password == null || password.isEmpty()) {
			return true;
		}else if (!(password.equals(passwordConfirmation))) {
			return true;
		}else {
			return false;
		}
	}
}