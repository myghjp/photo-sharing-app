package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Albums;

@Mapper
public interface AlbumMapper {
	
	/*ーーーEntryAlbumーーー*/
	
	public void insert(@Param("albums") Albums albums);
	
	/*ーーーSelectAlbumーーー*/
	
	public List<Albums> selectAlbumList(@Param("id") Integer id);
	
	public Albums select(@Param("id") Integer id);
	
	/*ーーーDeleteAlbumーーー*/
	
	public void delete(@Param("id") Integer id);
	
	/*比較を作成*/
	/*public int isIdAdminExisting(@Param("id") Integer id);*/
	
}