package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photo;

public interface PhotoService {
	
	public void add(Photo photo);
	
	public List<Photo> findAllById(int albumId);
	
	public void remove(int photoId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Photo findById(int photoId);
	
	public boolean isAlbum(int photoId,int albumId);
	
	public boolean isPhoto(int photoId,int userId);
	
}