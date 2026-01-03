package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photo;

public interface PhotoService {
	
	/*ーーーListPhotoーーー*/
	
	public List<Photo> getphotoList(int id);
	
	public void addPhoto(Photo photo);

	/*ーーーDeletePhotoーーー*/
	
	public Photo getPhoto(int id);
	
	public void removePhoto(int id);
	
	/*ーーーーーバリデーション作成中ーーーーーーーーーーー*/
	
	public boolean isCurrentAlbum(int photoId,int albumId);
	
	public boolean isC(int GroupId,int loginId);
	
	public boolean isB(int photoId,int loginId);
	
	
}