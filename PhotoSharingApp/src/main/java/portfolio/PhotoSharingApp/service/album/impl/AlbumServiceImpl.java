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
	
	/*グループID,アルバム名 を追加*/
	@Override
	public void addAlbum(Albums albums) {
		albumMapper.insertAlbum(albums);
	}
	
	/*アルバム一覧表示*/
	@Override
	public List<Albums> getAlbumList() {
		return albumMapper.selectAlbumName();
	}
	
	@Override
	public Albums getAlbum(int id) {
		return albumMapper.selectAlbum(id);
	}
	
	/*アルバム削除*/
	@Override
	public void deleteAlbum(int id) {
		albumMapper.deleteAlbum(id);
	}
	
}
