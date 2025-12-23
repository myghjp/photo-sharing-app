package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Albums;

public interface AlbumService {
	
	/*ーーーEntryAlbumーーー*/
	
	public void addAlbum(Albums albums);
	
	/*ーーーSelectAlbumーーー*/
	
	public List<Albums> getAlbumList(int id);
	
	public Albums getAlbum(int id);
	
	/*ーーーDeleteAlbumーーー*/
	
	public void deleteAlbum(int id);
	
	/*比較を作成*/
	public int isCurrentUser(int id);

}