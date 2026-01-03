package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Album;

public interface AlbumService {
	
	/*ーーーEntryAlbumーーー*/
	
	public void addAlbum(Album album);
	
	/*ーーーSelectAlbumーーー*/
	
	public List<Album> getAlbumList(int id);
	
	public Album getAlbum(int id);
	
	/*ーーーDeleteAlbumーーー*/
	
	public void deleteAlbum(int id);
	
	/*比較を作成*/
	public int isCurrentUser(int id);

}