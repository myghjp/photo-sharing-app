package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Album;

@Mapper
public interface AlbumMapper {
	
	public void insert(@Param("album") Album album);
	
	public void delete(Integer albumId);
	
	public List<Album> selectGroupAlbum(Integer groupId);
	
	public Album selectAlbum(Integer albumId);
	
	public Integer existsAlbumByAccountId(Integer albumId);
}