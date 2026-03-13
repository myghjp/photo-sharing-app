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
	public List<Photo> getPhotoInfo(int albumId){
		return photoMapper.selectByAlbumId(albumId);
	}
	
	@Override
	public void remove(int photoId) {
		photoMapper.delete(photoId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Photo findById(int photoId) {
		return photoMapper.selectByPhotoId(photoId);
	}
	
	@Override
	public boolean filenameExists(String filename) {
		if (photoMapper.selectByPhoto(filename) != null){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isAlbumPhoto(int photoId,int albumId) {
		if (photoMapper.selectAlbumIdByPhotoId(photoId) != albumId) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isUploader(int photoId,int userId) {
		if (photoMapper.selectAccountIdByPhotoId(photoId) != userId) {
			return true;
		} else {
			return false;
		}
	}
}