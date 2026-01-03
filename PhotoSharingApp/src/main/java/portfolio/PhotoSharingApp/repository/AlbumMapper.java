package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Album;

@Mapper
public interface AlbumMapper {
	
	/*ーーーEntryAlbumーーー*/
	
	public void insert(@Param("album") Album album);
	
	/*ーーーSelectAlbumーーー*/
	
	public List<Album> selectAlbumList(@Param("id") Integer id);
	
	public Album select(@Param("id") Integer id);
	
	/*ーーーDeleteAlbumーーー*/
	
	public void delete(@Param("id") Integer id);
	
	/*比較を作成*/
	public Integer selectUserId(@Param("id") Integer id);
	
}