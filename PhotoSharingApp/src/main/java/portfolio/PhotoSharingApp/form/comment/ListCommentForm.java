package portfolio.PhotoSharingApp.form.comment;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ListCommentForm {
	
	@NotBlank
	@Length(max = 50)
	private String comment;
}