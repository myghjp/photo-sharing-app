package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photos;

@Mapper
public interface PhotoMapper {
	
	public void insertPhoto(@Param("photos")Photos photos);
	
	public List<Photos> selectPhotoList();
	
	public Photos selectPhoto(@Param("id")Integer id);
	
	public void deletePhoto(@Param("id")Integer id);
}
