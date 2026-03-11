package portfolio.PhotoSharingApp.form;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AddMemberForm {
	
	@Email
	private String emailAddress;
}