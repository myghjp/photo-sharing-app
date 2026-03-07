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
	
	public List<Photo> getSelectPhotos(Integer albumId);
	
	public Photo getSelectPhoto(Integer photoId);
	
	public Integer getSelectAlbumId(Integer photoId);
	
	public Integer getSelectAccountId(Integer photoId);
}