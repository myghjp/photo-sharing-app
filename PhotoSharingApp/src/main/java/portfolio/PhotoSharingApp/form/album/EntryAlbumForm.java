package portfolio.PhotoSharingApp.form.album;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EntryAlbumForm {
	
	@NotBlank
	@Length(max = 50)
	private String albumName;
}
