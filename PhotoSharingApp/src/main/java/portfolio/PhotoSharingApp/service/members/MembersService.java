package portfolio.PhotoSharingApp.service.members;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;

public interface MembersService {
	
	/*ーAddMembersーーーーーーーーーーーーーーーーー*/
	
	/*メールアドレスを使用してアカウントIdが存在するかを確認*/
	public boolean isExistingAccountId(Accounts accounts);
	
	/*このメールアドレスは、このグループ内にいるメンバや管理者のアドレスが
	 * データベースで重複していないかを確認*/
	public boolean isExistingMembersId(Accounts accounts,Groups groups);
	
	/*メールアドレスを使用してアカウントIDを取得する*/
	public int selectAccountId(String emailAddress);
	
	/*グループのIDとアカウントのIDを追加する*/
	public void insertMembers(Members members);
	
	/*ーListMembersーーーーーーーーーーーーーーーーー*/
	
	/*このグループのメンバリストを取得する*/
	public List<Members> getMembersList(int groupId);
	
	/*管理者名を取得*/
	public String getAdminName(int id);
	
	/*グループ利用者IDとその名前を取得*/
	public Members getMemberName(int id);
	
	/*ーDeleteMembersーーーーーーーーーーーーーーーーー*/
	
	/*グループから利用者を削除*/
	public void deleteMember(int id);
	
}
