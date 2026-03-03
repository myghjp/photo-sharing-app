package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photo;

@Mapper
public interface PhotoMapper {
	
	public void insert(@Param("photo")Photo photo);
	
	public void delete(Integer photoId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public List<Photo> selectPhotos(Integer albumId);
	
	public Photo selectPhoto(Integer photoId);
	
	public Integer selectByAlbumId(Integer photoId);
	
	public Integer selectByAccountId(Integer photoId);
}