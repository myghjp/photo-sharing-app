package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photos;

public interface PhotoService {
	
	public void addPhoto(Photos photos);

	public List<Photos> getphotoList();
	
	public Photos getPhoto(int id);
	
	public void removePhoto(int id);
}
