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
	
	/*画像データ追加*/
	public void addPhoto(Photos photos) {
		photoMapper.insertPhoto(photos);
	}
	
	/*画像一覧取得*/
	public List<Photos> getphotoList(){
		return photoMapper.selectPhotoList();
	}
	
	public Photos getPhoto(int id) {
		return photoMapper.selectPhoto(id);
	}
	
	public void removePhoto(int id) {
		photoMapper.deletePhoto(id);
	}
	
}
