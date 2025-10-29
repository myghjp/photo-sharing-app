package portfolio.PhotoSharingApp.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EntryAccountForm {
	
	@NotBlank
	@Length(max = 50)
	private String user;
	
	/*登録済のアカウント名と重複していない*/
	/*ーーーーーーーーーーーーーーーーーーー*/
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@Length(min = 8, max = 12)
	private String pass;
	
	/*グループ化*/
	/*ーーーーーーーーーーーーーーーーーーー*/
	@NotBlank
	
	@Email
	private String email_address;
	/*登録済のアカウント名と重複していない*/
	/*ーーーーーーーーーーーーーーーーーーー*/
}