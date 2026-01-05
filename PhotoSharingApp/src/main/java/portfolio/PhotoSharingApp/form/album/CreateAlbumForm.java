package portfolio.PhotoSharingApp.form.album;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CreateAlbumForm {
	
	@NotBlank
	@Length(max = 50)
	private String albumName;
}