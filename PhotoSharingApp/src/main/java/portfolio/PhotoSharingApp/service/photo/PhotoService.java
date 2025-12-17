package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photos;

public interface PhotoService {
	
	/*ーーーListPhotoーーー*/
	
	/*photosテーブルの情報とアカウント名を取得*/
	public List<Photos> getphotoList(int id);
	
	public void addPhoto(Photos photos);

	/*ーーーDeletePhotoーーー*/
	
	/*このidと画像パス情報を取得*/
	public Photos getPhoto(int id);
	
	public void removePhoto(int id);
	
}