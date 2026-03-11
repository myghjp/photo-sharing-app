package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Album;

@Mapper
public interface AlbumMapper {
	
	public void insert(@Param("album") Album album);
	
	public void delete(Integer albumId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public List<Album> selectByGroupId(Integer groupId);
	
	public Album selectByAlbumId(Integer albumId);
	
	public Integer selectAccountIdByAlbumId(Integer albumId);
}