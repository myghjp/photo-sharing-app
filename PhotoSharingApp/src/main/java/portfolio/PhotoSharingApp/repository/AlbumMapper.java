package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Albums;

@Mapper
public interface AlbumMapper {
	
	/*ーEntryAlbumーーーーーーーーーーーーーーーーー*/
	
	/*グループIDとアルバム名を追加*/
	public void insertAlbum(@Param("albums") Albums albums);
	
	/*ーSelectAlbumーーーーーーーーーーーーーーーーー*/
	
	/*このグループ内のアルバムIDとアルバム名を取得*/
	public List<Albums> selectAlbumList(@Param("id") Integer id);
	
	/*アルバムIDとアルバム名を取得*/
	public Albums selectAlbum(@Param("id") Integer id);
	
	/*ーDeleteAlbumーーーーーーーーーーーーーーーーー*/
	
	/*アルバムの削除*/
	public void deleteAlbum(@Param("id") Integer id);
	
}
