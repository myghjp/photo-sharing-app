package portfolio.PhotoSharingApp.service.group;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Groups;

public interface GroupService {
	
	/*ーーーEntryGroupーーー*/
	
	public void entryGroup(Groups groups);
	
	public boolean isExistingGroupsData(Groups groups);
	
	/*ーーーSelectGroupーーー*/
	
	public List<Groups> getGroupList(int id);
	
	public Groups getGroupsData(int id);
	
	/*ーーーDeleteGroupーーー*/
	
	public void deleteGroup(int id);
	
	/*ーーー※DeleteAccountーーー*/
	/*public boolean isCreateGroupExisting(int id);*/
}
