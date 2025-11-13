package portfolio.PhotoSharingApp.form.group;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntryGroupForm {
	
	@NotBlank
	/*@Length(max = 50)*/
	private String groupName;
	
	/*private int accountId;*/

}
