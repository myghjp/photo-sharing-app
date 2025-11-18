package portfolio.PhotoSharingApp.service.members;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Members;

public interface MembersService {
	
	/*利用者を追加*/
	public void insertMembers(Members members);
	
	/*グループ内の利用者一覧を取得*/
	public List<Members> getMembersList(int groupId);
	
	/*グループからメンバーを削除*/
	public void deleteMembers(int id);
	
	/*重複確認*/
	public boolean isExistingMembersId(Accounts accounts);
	
}
