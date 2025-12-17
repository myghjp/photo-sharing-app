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
	
	/*photosテーブルの情報とアカウント名を取得*/
	@Override
	public List<Photos> getphotoList(int id){
		return photoMapper.selectPhotoList(id);
	}
	
	@Override
	public void addPhoto(Photos photos) {
		photoMapper.insertPhoto(photos);
	}
	
	/*ーーーDeletePhotoーーー*/
	
	/*このidと画像パス情報を取得*/
	@Override
	public Photos getPhoto(int id) {
		return photoMapper.selectPhoto(id);
	}
	
	@Override
	public void removePhoto(int id) {
		photoMapper.deletePhoto(id);
	}
}