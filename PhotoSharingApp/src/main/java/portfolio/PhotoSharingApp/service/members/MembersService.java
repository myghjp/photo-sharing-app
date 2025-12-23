package portfolio.PhotoSharingApp.service.members;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Accounts;
import portfolio.PhotoSharingApp.entity.Groups;
import portfolio.PhotoSharingApp.entity.Members;

public interface MembersService {
	
	/*ーーーAddMembersーーー*/
	
	public boolean isExistingAccountId(Accounts accounts);
	
	public boolean isExistingMembersId(Accounts accounts,Groups groups);
	
	public int selectAccountId(String emailAddress);
	
	public void insertMembers(Members members);
	
	/*ーーーListMembersーーー*/
	
	public List<Members> getMembersList(int groupId);
	
	public String getAdminName(int id);
	
	public Members getMemberName(int id);
	
	/*ーーーDeleteMembersーーー*/
	
	public void deleteMember(int id);
	
	/*比較を作成*/
	public int isCurrentUser(int id);
	
}