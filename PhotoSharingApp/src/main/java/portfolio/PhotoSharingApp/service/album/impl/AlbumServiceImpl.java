package portfolio.PhotoSharingApp.service.album.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Albums;
import portfolio.PhotoSharingApp.repository.AlbumMapper;
import portfolio.PhotoSharingApp.service.album.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	private AlbumMapper albumMapper;
	
	/*ーーーEntryAlbumーーー*/
	
	@Override
	public void addAlbum(Albums albums) {
		albumMapper.insert(albums);
	}
	
	/*ーーーSelectAlbumーーー*/
	
	@Override
	public List<Albums> getAlbumList(int id) {
		return albumMapper.selectAlbumList(id);
	}
	
	@Override
	public Albums getAlbum(int id) {
		return albumMapper.select(id);
	}
	
	/*ーーーDeleteAlbumーーー*/
	
	@Override
	public void deleteAlbum(int id) {
		albumMapper.delete(id);
	}
	
	/*比較を作成*/
}