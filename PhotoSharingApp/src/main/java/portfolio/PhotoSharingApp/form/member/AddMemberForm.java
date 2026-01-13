package portfolio.PhotoSharingApp.form.member;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AddMemberForm {
	
	/*@NotBlank*/
	@Email
	private String emailAddress;
}