package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photo;

@Mapper
public interface PhotoMapper {
	
	/*ーーーListPhotoーーー*/
	
	public List<Photo> selectPhotoItems(Integer id);
	
	public void insert(@Param("photo")Photo photo);
	
	/*ーーーDeletePhotoーーー*/
	
	public Photo selectPhoto(Integer id);
	
	public void delete(Integer id);
	
	/*バリデーション作成中*/
	public Integer existsByAlbumId(Integer id);
	
	public Integer existsByAccountId(Integer id);
	
}