package portfolio.PhotoSharingApp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import portfolio.PhotoSharingApp.entity.Albums;

@Mapper
public interface AlbumMapper {
	
	/*グループID,アルバム名 を追加*/
	public void insertAlbumData(@Param("albums") Albums albums);
	
	/*アルバム一覧表示*/
	public List<Albums> selectAlbumName();
	
	/*アルバム削除*/
	public void deleteAlbum(@Param("id") int id);
	
}
