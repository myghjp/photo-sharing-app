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
	
	/*ーEntryAlbumーーーーーーーーーーーーーーーーー*/
	
	/*グループIDとアルバム名を追加*/
	@Override
	public void addAlbum(Albums albums) {
		albumMapper.insertAlbum(albums);
	}
	
	/*ーSelectAlbumーーーーーーーーーーーーーーーーー*/
	
	/*このグループ内のアルバムIDとアルバム名を取得*/
	@Override
	public List<Albums> getAlbumList(int id) {
		return albumMapper.selectAlbumList(id);
	}
	
	/*アルバムIDとアルバム名を取得*/
	@Override
	public Albums getAlbum(int id) {
		return albumMapper.selectAlbum(id);
	}
	
	/*ーDeleteAlbumーーーーーーーーーーーーーーーーー*/
	
	/*アルバムの削除*/
	@Override
	public void deleteAlbum(int id) {
		albumMapper.deleteAlbum(id);
	}
	
}
