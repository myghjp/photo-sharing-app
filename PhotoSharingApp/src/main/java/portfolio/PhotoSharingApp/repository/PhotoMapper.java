package portfolio.PhotoSharingApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Photos;

@Mapper
public interface PhotoMapper {
	
	public void insertPhoto(@Param("photos")Photos photos);
	
}
