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
	public Photos getSelectAll(int id);
	
	
	
	/*この写真はこのアカウントのもの？(Principal×)*/
	
	public boolean isCurrentUser(int photoId,int groupId);
	
	
	
}