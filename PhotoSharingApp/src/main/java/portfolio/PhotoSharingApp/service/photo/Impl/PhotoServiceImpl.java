package portfolio.PhotoSharingApp.service.photo.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Photos;
import portfolio.PhotoSharingApp.repository.PhotoMapper;
import portfolio.PhotoSharingApp.service.photo.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoMapper photoMapper;
	
	/*ーーーListPhotoーーー*/
	
	@Override
	public List<Photos> getphotoList(int id){
		return photoMapper.selectPhotoItems(id);
	}
	
	@Override
	public void addPhoto(Photos photos) {
		photoMapper.insert(photos);
	}
	
	/*ーーーDeletePhotoーーー*/
	
	@Override
	public Photos getPhoto(int id) {
		return photoMapper.select(id);
	}
	
	@Override
	public void removePhoto(int id) {
		photoMapper.delete(id);
	}
	
	/*ーーーーーバリデーション作成中ーーーーーーーーーーー*/
	
	@Override
	public boolean isCurrentAlbum(int photoId,int albumId) {
		if (photoMapper.selectByAlbumId(photoId) != albumId) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isCurrentUser(int photoId,int loginId) {
		if (photoMapper.selectByAccountId(photoId) == loginId) {
			return true;
		} else {
			return false;
		}
	}
	
}