package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	public void create(Group group);
	
	public List<Group> getGroupList(int id);
	
	public void delete(int groupId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Group findById(int groupId);
	
	public Group getGroupAdminInfo(int groupId);
	
	public boolean existsGroupName(String groupName);
	
	public boolean hasGroupOwner(int groupId,int userId);
	
	public boolean hasCreateGroup(int userId);
	
}