package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Albums;

public interface AlbumService {
	
	/*ーEntryAlbumーーーーーーーーーーーーーーーーー*/
	
	/*グループIDとアルバム名を追加*/
	public void addAlbum(Albums albums);
	
	/*ーSelectAlbumーーーーーーーーーーーーーーーーー*/
	
	/*このグループ内のアルバムIDとアルバム名を取得*/
	public List<Albums> getAlbumList(int id);
	
	/*アルバムIDとアルバム名を取得*/
	public Albums getAlbum(int id);
	
	/*ーDeleteAlbumーーーーーーーーーーーーーーーーー*/
	
	/*アルバムの削除*/
	public void deleteAlbum(int id);

}
