package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photo;

public interface PhotoService {
	
	public void add(Photo photo);
	
	public List<Photo> getPhotoInfo(int albumId);
	
	public void remove(int photoId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Photo findById(int photoId);
	
	public boolean isAlbumPhoto(int photoId,int albumId);
	
	public boolean isUploader(int photoId,int userId);
}