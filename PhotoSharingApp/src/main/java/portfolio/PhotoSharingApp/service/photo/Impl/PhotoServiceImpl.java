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
	public List<Photo> findAllById(int albumId){
		return photoMapper.selectPhotos(albumId);
	}
	
	@Override
	public void remove(int photoId) {
		photoMapper.delete(photoId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Photo findById(int photoId) {
		return photoMapper.selectPhoto(photoId);
	}
	
	@Override
	public boolean isCurrentAlbum(int photoId,int albumId) {
		if (photoMapper.selectByAlbumId(photoId) != albumId) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isCurrentPhoto(int photoId,int userId) {
		if (photoMapper.selectByAccountId(photoId) != userId) {
			return true;
		} else {
			return false;
		}
	}
}