package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	/*ーーーEntryGroupーーー*/
	
	public void entryGroup(Group group);
	
	public boolean isExistingGroupsData(Group group);
	
	/*ーーーSelectGroupーーー*/
	
	public List<Group> getGroupList(int id);
	
	public Group getGroupsData(int id);
	
	/*ーーーDeleteGroupーーー*/
	
	public void deleteGroup(int id);
	
	/*比較を作成*/
	public int isCurrentUser(int id);
}
