package portfolio.PhotoSharingApp.form.group;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGroupForm {
	
	@NotBlank
	@Length(max = 20)
	private String groupName;
}