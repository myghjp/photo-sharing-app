package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photo;

@Mapper
public interface PhotoMapper {
	
	/*ーーーListPhotoーーー*/
	
	public List<Photo> selectPhotoItems(@Param("id")Integer id);
	
	public void insert(@Param("photo")Photo photo);
	
	/*ーーーDeletePhotoーーー*/
	
	public Photo select(@Param("id")Integer id);
	
	public void delete(@Param("id")Integer id);
	
	
	/*バリデーション作成中*/
	public Integer selectByAlbumId(@Param("id") Integer id);
	
	public Integer selectByAccountId(@Param("id")Integer id);
	
	
	
}