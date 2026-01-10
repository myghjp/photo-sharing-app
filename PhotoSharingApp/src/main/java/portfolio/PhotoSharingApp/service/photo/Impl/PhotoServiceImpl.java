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
	public void addPhoto(Photo photo) {
		photoMapper.insert(photo);
	}
	
	@Override
	public void removePhoto(int id) {
		photoMapper.delete(id);
	}
	
	/*写真のテーブル情報とアカウント名を取得*/
	@Override
	public List<Photo> getphotoList(int id){
		return photoMapper.selectPhotoItems(id);
	}
	
	
	@Override
	public Photo getPhoto(int id) {
		return photoMapper.selectPhoto(id);
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
	
	/*自身がグループの管理者であるかを確認*/
	@Override
	public boolean isCurrentGroupAdmin(int GroupId,int loginId) {
		if (GroupId != loginId) {
			return true;
		} else {
			return false;
		}
	}
	
	/*自身が追加した写真なのかを確認*/
	@Override
	public boolean isCurrentPhoto(int photoId,int loginId) {
		if (photoMapper.existsByAccountId(photoId) != loginId) {
			return true;
		} else {
			return false;
		}
	}
}