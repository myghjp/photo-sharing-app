package portfolio.PhotoSharingApp.service;

import java.util.List;

import portfolio.PhotoSharingApp.entity.Group;

public interface GroupService {
	
	public void create(Group group);
	
	public List<Group> getGroupListByUserId(int id);
	
	public void delete(int groupId);
	
	/*ーーーーーーーーーーーーーーーーーーーー*/
	
	public Group findGroupById(int groupId);
	
	public Group getAdminInfoById(int groupId);
	
	public boolean groupNameExists(String groupName);
	
	public boolean isOwner(int groupId,int userId);
	
	public boolean groupExists(int userId);
	
}