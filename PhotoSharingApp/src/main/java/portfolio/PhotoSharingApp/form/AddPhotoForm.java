package portfolio.PhotoSharingApp.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddPhotoForm {
	
	private MultipartFile photo;
}
