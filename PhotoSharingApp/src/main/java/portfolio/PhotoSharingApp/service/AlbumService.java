package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Album;

public interface AlbumService {
	
	public void add(Album album);
	
	public List<Album> getAlbumList(int groupId);
	
	public int getCountAlbums(int groupId);
	
	public void delete(int albumId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Album findById(int albumId);
	
	public boolean hasCreateAlbum(int albumId,int userId);
	
}