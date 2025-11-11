package portfolio.PhotoSharingApp.form.members;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddMembersForm {
	
	@NotBlank
	@Email
	private String emailAddress;

}
