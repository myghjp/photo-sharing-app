package portfolio.PhotoSharingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portfolio.PhotoSharingApp.entity.Album;
import portfolio.PhotoSharingApp.repository.AlbumMapper;
import portfolio.PhotoSharingApp.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{
	
	@Autowired
	private AlbumMapper albumMapper;
	
	@Override
	public void add(Album album) {
		albumMapper.insert(album);
	}
	
	@Override
	public List<Album> getGroupAlbumInfo(int groupId) {
		return albumMapper.selectByGroupId(groupId);
	}
	
	@Override
	public int getGroupAlbumCount(int groupId) {
		return albumMapper.countByGroupId(groupId);
	}
	@Override
	public void delete(int albumId) {
		albumMapper.delete(albumId);
	}
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	@Override
	public Album findById(int albumId) {
		return albumMapper.selectByAlbumId(albumId);
	}
	
	@Override
	public boolean isAlbumCreator(int albumId,int userId) {
		if (albumMapper.selectAccountIdByAlbumId(albumId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}