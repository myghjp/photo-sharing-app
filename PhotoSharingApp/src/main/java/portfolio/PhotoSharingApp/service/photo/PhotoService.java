package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photo;

public interface PhotoService {
	
	public void add(Photo photo);
	
	public void remove(int photoId);
	
	public List<Photo> findAllById(int albumId);
	
	public Photo findById(int photoId);
	
	public boolean isCurrentAlbum(int photoId,int albumId);
	
	public boolean isCurrentPhoto(int photoId,int userId);
	
}