package portfolio.PhotoSharingApp.service.album.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.repository.AlbumMapper;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	private AlbumMapper albumMapper;
	
	/*ーーーEntryAlbumーーー*/
	
	@Override
	public void addAlbum(Album album) {
		albumMapper.insert(album);
	}
	
	/*ーーーSelectAlbumーーー*/
	
	@Override
	public List<Album> getAlbumList(int id) {
		return albumMapper.selectAlbumList(id);
	}
	
	@Override
	public Album getAlbum(int id) {
		return albumMapper.select(id);
	}
	
	/*ーーーDeleteAlbumーーー*/
	
	@Override
	public void deleteAlbum(int id) {
		albumMapper.delete(id);
	}
	
	/*比較を作成*/
	@Override
	public int isCurrentUser(int id) {
		return albumMapper.selectUserId(id);
	}
	
}