package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Album;

public interface AlbumService {
	
	public void add(Album album);
	
	public void delete(int albumId);
	
	public List<Album> findAllById(int groupId);
	
	public Album findById(int albumId);
	
	public boolean isAlbum(int albumId,int userId);
}