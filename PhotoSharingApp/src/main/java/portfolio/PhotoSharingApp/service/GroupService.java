package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	public void create(Group group);
	
	public List<Group> getAffiliationGroupInfo(int id);
	
	public void delete(int groupId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Group findById(int groupId);
	
	public Group getAdminInfo(int groupId);
	
	public boolean isGroupNameDuplicate(String groupName);
	
	public boolean isGroupCreator(int groupId,int userId);
	
	public boolean createdGroupExists(int userId);
	
}