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
	
	public List<Album> selectGroupAlbum(Integer id);
	
	public Album selectAlbum(Integer id);
	
	/*ーーーDeleteAlbumーーー*/
	
	public void delete(Integer id);
	
	/*比較を作成*/
	public Integer existsAlbumByAccountId(Integer id);
	
}