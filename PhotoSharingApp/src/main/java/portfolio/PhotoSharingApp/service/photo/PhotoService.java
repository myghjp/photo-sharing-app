package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photo;

public interface PhotoService {
	
	public void addPhoto(Photo photo);
	
	public void removePhoto(int id);
	
	public List<Photo> getphotoList(int id);
	
	public Photo getPhoto(int id);
	
	public boolean isCurrentAlbum(int photoId,int albumId);
	
	public boolean isCurrentGroupAdmin(int GroupId,int loginId);
	
	public boolean isCurrentPhoto(int photoId,int loginId);
	
}