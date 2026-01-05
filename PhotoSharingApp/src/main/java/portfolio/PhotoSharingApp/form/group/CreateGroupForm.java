package portfolio.PhotoSharingApp.form.group;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CreateGroupForm {
	
	@NotBlank
	@Length(max = 50)
	private String groupName;
}