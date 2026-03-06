package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Album;

public interface AlbumService {
	
	public void add(Album album);
	
	public List<Album> getAlbumListByGroupsId(int groupId);
	
	public int getCountAlbumsByGroupsId(int groupId);
	
	public void delete(int albumId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Album findById(int albumId);
	
	public boolean isAlbum(int albumId,int userId);
	
}