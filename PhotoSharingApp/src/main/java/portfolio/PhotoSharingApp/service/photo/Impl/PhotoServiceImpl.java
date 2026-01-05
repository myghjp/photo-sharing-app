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
	
	/*ーーーListPhotoーーー*/
	
	@Override
	public List<Photo> getphotoList(int id){
		return photoMapper.selectPhotoItems(id);
	}
	
	@Override
	public void addPhoto(Photo photo) {
		photoMapper.insert(photo);
	}
	
	/*ーーーDeletePhotoーーー*/
	
	@Override
	public Photo getPhoto(int id) {
		return photoMapper.selectPhoto(id);
	}
	
	@Override
	public void removePhoto(int id) {
		photoMapper.delete(id);
	}
	
	@Override
	public boolean isCurrentAlbum(int photoId,int albumId) {
		if (photoMapper.existsByAlbumId(photoId) != albumId) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isCurrentGroupAdmin(int GroupId,int loginId) {
		if (GroupId != loginId) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isCurrentPhoto(int photoId,int loginId) {
		if (photoMapper.existsByAccountId(photoId) != loginId) {
			return true;
		} else {
			return false;
		}
	}
	
}