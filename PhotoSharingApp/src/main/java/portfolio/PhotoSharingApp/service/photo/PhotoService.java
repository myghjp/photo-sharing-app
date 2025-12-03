package portfolio.PhotoSharingApp.service.photo;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Photos;

public interface PhotoService {
	
	/*ーListPhotoーーーーーーーーーーーーーーーーー*/
	
	/*photosテーブルの情報とアカウント名を取得*/
	public List<Photos> getphotoList(int id);
	
	/*photosテーブル情報を追加*/
	public void addPhoto(Photos photos);
	
	public boolean isPathExisting(String photo);

	/*ーDeletePhotoーーーーーーーーーーーーーーーーー*/
	
	/*このidと画像パス情報を取得*/
	public Photos getPhoto(int id);
	
	/*データベースのパス情報を削除する*/
	public void removePhoto(int id);
	
	
	
}
