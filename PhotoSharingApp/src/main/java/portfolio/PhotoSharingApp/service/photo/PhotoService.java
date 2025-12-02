package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photos;

public interface PhotoService {
	
	public void addPhoto(Photos photos);

	public List<Photos> getphotoList(int id);
	
	public Photos getPhoto(int id);
	
	public void removePhoto(int id);
	
	public boolean isPathExisting(String photo);
}
