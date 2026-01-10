package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photo;

@Mapper
public interface PhotoMapper {
	
	public void insert(@Param("photo")Photo photo);
	
	public void delete(Integer id);
	
	public List<Photo> selectPhotoItems(Integer id);
	
	public Photo selectPhoto(Integer id);
	
	public Integer existsByAlbumId(Integer id);
	
	public Integer existsByAccountId(Integer id);
}