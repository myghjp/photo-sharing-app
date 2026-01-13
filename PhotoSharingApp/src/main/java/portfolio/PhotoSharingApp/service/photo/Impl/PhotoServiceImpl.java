package portfolio.PhotoSharingApp.service.photo.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Photo;
import portfolio.PhotoSharingApp.repository.PhotoMapper;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Override
	public void add(Photo photo) {
		photoMapper.insert(photo);
	}
	
	@Override
	public void remove(int photoId) {
		photoMapper.delete(photoId);
	}
	
	/*写真のテーブル情報とアカウント名を取得*/
	@Override
	public List<Photo> findAllById(int albumId){
		return photoMapper.selectPhotoItems(albumId);
	}
	
	
	@Override
	public Photo findById(int photoId) {
		return photoMapper.selectPhoto(photoId);
	}
	
	/*このグループの写真なのかを確認*/
	@Override
	public boolean isCurrentAlbum(int photoId,int albumId) {
		if (photoMapper.existsByAlbumId(photoId) != albumId) {
			return true;
		} else {
			return false;
		}
	}
	
	/*自身が追加した写真なのかを確認*/
	@Override
	public boolean isCurrentPhoto(int photoId,int userId) {
		if (photoMapper.existsByAccountId(photoId) != userId) {
			return true;
		} else {
			return false;
		}
	}
}