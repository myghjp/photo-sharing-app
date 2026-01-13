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
	
	@Override
	public void add(Album album) {
		albumMapper.insert(album);
	}
	
	@Override
	public List<Album> findAllById(int groupId) {
		return albumMapper.selectAlbums(groupId);
	}
	
	@Override
	public void delete(int albumId) {
		albumMapper.delete(albumId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Album findById(int albumId) {
		return albumMapper.selectAlbum(albumId);
	}
	
	@Override
	public boolean isAlbum(int albumId,int userId) {
		if (albumMapper.selectGroupByAccountId(albumId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}