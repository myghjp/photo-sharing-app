package portfolio.PhotoSharingApp.form.comment;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ListCommentForm {
	
	@NotBlank
	@Length(max = 100)
	private String comment;
}