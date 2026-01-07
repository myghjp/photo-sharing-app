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
	
	public Photo selectPhoto(@Param("id")Integer id);
	
	public void delete(@Param("id")Integer id);
	
	/*バリデーション作成中*/
	public Integer existsByAlbumId(@Param("id") Integer id);
	
	public Integer existsByAccountId(@Param("id")Integer id);
	
}