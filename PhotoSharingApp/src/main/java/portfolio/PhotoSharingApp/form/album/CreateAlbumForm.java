package portfolio.PhotoSharingApp.form.album;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAlbumForm {
	
	@NotBlank
	@Length(max = 20)
	private String albumName;
}