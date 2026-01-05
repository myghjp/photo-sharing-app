package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	/*ーーーEntryGroupーーー*/
	
	public void entryGroup(Group group);
	
	public boolean isExistingGroup(Group group);
	
	/*ーーーSelectGroupーーー*/
	
	public List<Group> getGroupList(int id);
	
	public Group getGroup(int id);
	
	/*ーーーDeleteGroupーーー*/
	
	public void deleteGroup(int id);
	
	/*比較を作成*/
	public boolean isCurrentAccount(int GroupId,int loginId);
}
