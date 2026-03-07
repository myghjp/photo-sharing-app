package portfolio.PhotoSharingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Photo;
import portfolio.PhotoSharingApp.repository.PhotoMapper;
import portfolio.PhotoSharingApp.service.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoMapper photoMapper;
	
	@Override
	public void add(Photo photo) {
		photoMapper.insert(photo);
	}
	
	@Override
	public List<Photo> getPhotoList(int albumId){
		return photoMapper.getSelectPhotos(albumId);
	}
	
	@Override
	public void remove(int photoId) {
		photoMapper.delete(photoId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Photo findById(int photoId) {
		return photoMapper.getSelectPhoto(photoId);
	}
	
	@Override
	public boolean hasAlbumPhoto(int photoId,int albumId) {
		if (photoMapper.getSelectAlbumId(photoId) != albumId) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean hasAddPhoto(int photoId,int userId) {
		if (photoMapper.getSelectAccountId(photoId) != userId) {
			return true;
		} else {
			return false;
		}
	}
}