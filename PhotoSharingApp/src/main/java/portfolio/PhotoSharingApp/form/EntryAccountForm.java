package portfolio.PhotoSharingApp.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EntryAccountForm {
	
	@NotNull
	@Length(max = 50)
	private String user;
	
	/*50文字以内である,
	 * 登録済のアカウント名と重複していない,
	 * 入力されている*/
	/*ーーーーーーーーーーーーーーーーーーー*/
	
	/*正規表現しかない？*/
	
	
	@NotNull
	@Size(min = 4, max = 12)
	private String pass;
	/*半角英数字のみ,
	 * 8文字以上12文字以下,
	 * 入力されている*/
	/*ーーーーーーーーーーーーーーーーーーー*/
	
	/*EmailValidator？？？*/
	
	@NotNull
	@Email
	private String email_address;
	/*メールアドレスの形式であること,
	 * 登録済のアドレスと重複していない,
	 * 入力されている*/
	/*ーーーーーーーーーーーーーーーーーーー*/
}