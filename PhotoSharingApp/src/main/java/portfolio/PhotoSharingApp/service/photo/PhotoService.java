package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photos;

public interface PhotoService {
	
	/*ーーーListPhotoーーー*/
	
	public List<Photos> getphotoList(int id);
	
	public void addPhoto(Photos photos);

	/*ーーーDeletePhotoーーー*/
	
	public Photos getPhoto(int id);
	
	public void removePhoto(int id);
	
	/*ーーーーーバリデーション作成中ーーーーーーーーーーー*/
	
	public boolean isCurrentAlbum(int photoId,int albumId);
	
	public boolean isCurrentUser(int photoId,int loginId);
	
	
	
}