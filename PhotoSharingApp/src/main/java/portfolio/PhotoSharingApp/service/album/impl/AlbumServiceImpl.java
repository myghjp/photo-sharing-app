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
	public void delete(int albumId) {
		albumMapper.delete(albumId);
	}
	
	/*このグループのアルバムIDとアルバム名を取得*/
	@Override
	public List<Album> findAllById(int groupId) {
		return albumMapper.selectGroupAlbum(groupId);
	}
	
	/*アルバムIDとアルバム名を取得*/
	@Override
	public Album findById(int albumId) {
		return albumMapper.selectAlbum(albumId);
	}
	
	/*自身が作成したアルバムであるかを確認*/
	@Override
	public boolean isAlbum(int albumId,int userId) {
		if (albumMapper.existsAlbumByAccountId(albumId) == userId) {
			return false;
		} else {
			return true;
		}
	}
}