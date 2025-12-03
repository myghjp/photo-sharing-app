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
	
	/*ーListPhotoーーーーーーーーーーーーーーーーー*/
	
	/*photosテーブルの情報とアカウント名を取得*/
	@Override
	public List<Photos> getphotoList(int id){
		return photoMapper.selectPhotoList(id);
	}
	
	/*photosテーブル情報を追加*/
	@Override
	public void addPhoto(Photos photos) {
		photoMapper.insertPhoto(photos);
	}
	
	/*ーDeletePhotoーーーーーーーーーーーーーーーーー*/
	
	/*このidと画像パス情報を取得*/
	@Override
	public Photos getPhoto(int id) {
		return photoMapper.selectPhoto(id);
	}
	
	/*データベースのパス情報を削除する*/
	@Override
	public void removePhoto(int id) {
		photoMapper.deletePhoto(id);
	}
}