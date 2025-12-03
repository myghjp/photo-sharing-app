package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photos;

@Mapper
public interface PhotoMapper {
	
	/*ーListPhotoーーーーーーーーーーーーーーーーー*/
	
	/*photosテーブルの情報とアカウント名を取得*/
	public List<Photos> selectPhotoList(@Param("id")Integer id);
	
	/*photosテーブル情報を追加*/
	public void insertPhoto(@Param("photos")Photos photos);
	
	/*ーDeletePhotoーーーーーーーーーーーーーーーーー*/
	
	/*このidと画像パス情報を取得*/
	public Photos selectPhoto(@Param("id")Integer id);
	
	/*データベースのパス情報を削除する*/
	public void deletePhoto(@Param("id")Integer id);
	
}