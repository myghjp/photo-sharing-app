package portfolio.PhotoSharingApp.service.album;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Albums;

public interface AlbumService {
	
	/*グループID,アルバム名 を追加*/
	public void addAlbum(Albums albums);
	
	/*アルバム一覧表示*/
	public List<Albums> getAlbumList(int id);
	
	public Albums getAlbum(int id);
	
	/*アルバム削除*/
	public void deleteAlbum(int id);

}
