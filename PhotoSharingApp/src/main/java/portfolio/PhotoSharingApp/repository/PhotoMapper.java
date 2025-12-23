package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photos;

@Mapper
public interface PhotoMapper {
	
	/*ーーーListPhotoーーー*/
	
	public List<Photos> selectPhotoItems(@Param("id")Integer id);
	
	public void insert(@Param("photos")Photos photos);
	
	/*ーーーDeletePhotoーーー*/
	
	public Photos select(@Param("id")Integer id);
	
	public void delete(@Param("id")Integer id);
	
	
	
	
	
	/*比較を作成*/
	public Integer selectUserId(@Param("id") Integer id);
	
	
}