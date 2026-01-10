package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Album;

public interface AlbumService {
	
	public void addAlbum(Album album);
	
	public void deleteAlbum(int id);
	
	public List<Album> getAlbumList(int id);
	
	public Album getAlbum(int id);
	
	public boolean isFindCreateAlbum(int albumId,int loginId);
}