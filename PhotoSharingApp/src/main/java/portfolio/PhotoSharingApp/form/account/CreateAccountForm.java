package portfolio.PhotoSharingApp.form.account;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateAccountForm {
	
	@NotBlank
	@Length(max = 20)
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{8,12}$")
	private String password;
	
	@NotBlank
	@Email
	private String emailAddress;
}