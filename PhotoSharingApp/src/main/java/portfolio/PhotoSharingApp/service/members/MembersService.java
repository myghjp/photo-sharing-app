package portfolio.PhotoSharingApp.service.members;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Members;

public interface MembersService {
	
	/*グループ内の利用者一覧を取得*/
	public List<Members> getMembersList(int id);
	
	/*グループからメンバーを削除*/

	
}
