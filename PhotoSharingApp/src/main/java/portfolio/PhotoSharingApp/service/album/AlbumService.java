package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Albums;

public interface AlbumService {
	
	/*グループID,アルバム名 を追加*/
	public void insertEntryAlbum(Albums albums);
	
	/*アルバム一覧表示*/
	public List<Albums> getAlbumList();

}
