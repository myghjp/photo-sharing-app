package portfolio.PhotoSharingApp.form.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EditAccountForm {
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{8,12}$")
	private String password;
}